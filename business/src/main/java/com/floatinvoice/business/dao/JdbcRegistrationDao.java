package com.floatinvoice.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.transaction.annotation.Transactional;
import com.floatinvoice.common.RegistrationStatusEnum;
import com.floatinvoice.common.UUIDGenerator;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.RegistrationStep2CorpDtlsMsg;
import com.floatinvoice.messages.RegistrationStep3UserPersonalDtlsMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;

@Transactional
public class JdbcRegistrationDao implements RegistrationDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private LobHandler lobHandler;
	private OrgReadDao orgReadDao;
	final static String sql = "INSERT INTO DOCS_STORE (FILE_NAME, FILE_BYTES, INSERT_DT, COMPANY_ID, USER_ID, REF_ID, REQUEST_ID, SOURCE_APP, CATEGORY) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public JdbcRegistrationDao(){
		
	}
	
	public JdbcRegistrationDao( DataSource dataSource, LobHandler lobHandler, OrgReadDao orgReadDao ){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.lobHandler = lobHandler;
		this.orgReadDao = orgReadDao;
	}
	
	private boolean updateRegistrationStatus(String userEmail, int statusCode){
		boolean rowUpdated = false;
		final String sql = "UPDATE CLIENT_LOGIN_INFO SET REGISTRATION_STATUS = :status where EMAIL = :email";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", userEmail);
		paramMap.put("status", statusCode);
		int row = jdbcTemplate.update(sql, paramMap);
		if (row == 1) {
			rowUpdated = true;
		}
		return rowUpdated;
	}
	
	@Override
	public BaseMsg registerSignInInfo(String userEmail, String password,
			String confirmedPassword, int regCode) {
		BaseMsg baseMsg = null;
		final String sql = "INSERT INTO CLIENT_LOGIN_INFO ( EMAIL, PASSWORD, INSERT_DT, REGISTRATION_STATUS) VALUES "
				+ "(:userEmail, :passwd, :insertDt, :regStatus)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userEmail", userEmail);
		paramMap.put("passwd", password);
		paramMap.put("insertDt", new Date());
		paramMap.put("regStatus", regCode);
		int row = jdbcTemplate.update(sql, paramMap);
		if(row == 1) {
			baseMsg = new BaseMsg();
		}
		return baseMsg;
	}

	@Override
	public BaseMsg registerOrgInfo(RegistrationStep2CorpDtlsMsg msg) {
		BaseMsg baseMsg = null;
		final String sql = "INSERT INTO ORGANIZATION_INFO (ACRONYM, COMPANY_NAME, STREET, CITY, STATE, ZIP_CODE, COUNTRY, PHONE_NO, INSERT_DT, UPDATE_DT, UPDATE_BY, CREATED_BY, ORG_TYPE)"
				+ " VALUES (:acro, :companyName, :street, :city, :state, :zipCode, 'INDIA', :phoneNo, :insertDt, :updateDt, :updateBy, :createdBy, :orgType)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acro", msg.getAcronym());
		paramMap.put("companyName", msg.getCompName());
		paramMap.put("street", msg.getStreet());
		paramMap.put("city", msg.getCity());
		paramMap.put("state", msg.getState());
		paramMap.put("zipCode", msg.getZipCode());
		paramMap.put("phoneNo", msg.getPhoneNo());		
		paramMap.put("insertDt", new Date());		
		paramMap.put("updateDt", new Date());		
		paramMap.put("updateBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());	// This is HACK to allow admin user to create org obo SELLER org	
		paramMap.put("createdBy", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());		// This is HACK to allow admin user to create org obo SELLER org
		paramMap.put("orgType", msg.getOrgType());		
		int row = jdbcTemplate.update(sql, paramMap);
		if(row == 1){
			
			final String uoMapSql = "INSERT INTO USER_ORGANIZATION_MAP (USER_ID, COMPANY_ID, INSERT_DT) "
					+ " VALUES ( (SELECT USER_ID FROM CLIENT_LOGIN_INFO WHERE EMAIL = :email),"
					+ " (SELECT COMPANY_ID FROM ORGANIZATION_INFO WHERE ACRONYM = :acro), "
					+ " SYSDATE()) ";
			
			Map<String, Object> map = new HashMap<>();
			map.put("email", msg.getUser() == null ? UserContext.getUserName() : msg.getUser());// This is HACK to allow admin user to create org obo SELLER org
			map.put("acro", msg.getAcronym());
			int nestedRow = jdbcTemplate.update(uoMapSql, map);
			if( nestedRow == 1 && updateRegistrationStatus(UserContext.getUserName(), RegistrationStatusEnum.ORG.getCode())){
				baseMsg = new BaseMsg();
			}
		}		
		return baseMsg;		
	}

	@Override
	public BaseMsg registerUserBankInfo(RegistrationStep3UserPersonalDtlsMsg msg) {
		BaseMsg baseMsg = null;
		String userIdSql = "SELECT USER_ID FROM CLIENT_LOGIN_INFO WHERE EMAIL = :email";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("email", UserContext.getUserName());
		Map<String, Object> userIdSqlResult = jdbcTemplate.queryForObject(userIdSql, params, new ColumnMapRowMapper());
		final String sql = "INSERT INTO SME_USER_INFO (USER_ID, BANK_ACCOUNT_NO, BANK_IFSC_CODE, BANK_NAME, DIRECTOR_FNAME, DIRECTOR_LNAME, PAN_CARD_NO, AADHAR_CARD_ID)"
				+ " VALUES (:userId, :bankAcctNo, :IFSCCd, :bankName, :dirFName, :dirLName, :panCard, :aadharId)";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userIdSqlResult.get("USER_ID"));
		paramMap.put("bankAcctNo", msg.getBankAcctNo());		
		paramMap.put("IFSCCd", msg.getIfscCode());
		paramMap.put("bankName", msg.getBankName());
		paramMap.put("dirFName", msg.getDirectorFName());
		paramMap.put("dirLName", msg.getDirectorLName());
		paramMap.put("panCard", msg.getPanCardNo());
		paramMap.put("aadharId", msg.getAadharCardId());
		int row = jdbcTemplate.update(sql, paramMap);
		if( row == 1 && updateRegistrationStatus(UserContext.getUserName(), RegistrationStatusEnum.USER.getCode())){
			baseMsg = new BaseMsg();
		}
		return baseMsg;
		
	}

	@Override
	public BaseMsg fileUpload(final UploadMessage msg) throws Exception {
		//final int userId = orgReadDao.findUserId(UserContext.getUserName());	
		Map<String, Object> clientDtls = orgReadDao.findOrgAndUserId(UserContext.getUserName());
		final int orgId = (int) clientDtls.get("COMPANY_ID");
		final int userId = (int) clientDtls.get("USER_ID");
		final LobCreator lobCreator = lobHandler.getLobCreator();
		final byte [] bytes = msg.getFile().getBytes();
		jdbcTemplate.getJdbcOperations().update( new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {

				final PreparedStatement ps = conn.prepareStatement(sql);
				try {
					ps.setString(1, msg.getFileName());
					lobCreator.setBlobAsBytes(ps, 2, bytes);
					ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
					ps.setInt(4, orgId);
					ps.setInt(5, userId);			
					ps.setString(6, UUIDGenerator.newRefId());
					ps.setString(7, UUID.randomUUID().toString());
					ps.setInt(8, 0);
					ps.setString(9, msg.getCategory());
				} catch (SQLException e) {
					throw e;
				}
				return ps;
			}
		});
		lobCreator.close();
		BaseMsg response = new BaseMsg();	
		response.addInfoMsg("File uploaded successfully", HttpStatus.OK.value());
		return response;
	}

	@Override
	public ListMsg<SupportDocDtls> summary(String acronym) {
		int orgId = 0;
		if ( acronym == null ){
			orgId = orgReadDao.findOrgIdByEmail(UserContext.getUserName());
		}else{
			Map<String, Object> orgInfo = orgReadDao.findOrgId(acronym);
			orgId = (int) orgInfo.get("COMPANY_ID");
		}
		final String sql = "SELECT DS.FILE_NAME, DS.REF_ID, DS.INSERT_DT, DS.CATEGORY, CLI.EMAIL FROM DOCS_STORE DS"
				+ " JOIN CLIENT_LOGIN_INFO CLI"
				+ " ON CLI.USER_ID = DS.USER_ID"
				+ " WHERE DS.COMPANY_ID = :orgId";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orgId", orgId);
		List<SupportDocDtls> list = jdbcTemplate.query(sql, paramMap, new SupportDocRowMapper());
		ListMsg<SupportDocDtls> result = new ListMsg<>(list);
		return result;
	}

	
	private class SupportDocRowMapper implements RowMapper<SupportDocDtls>{

		@Override
		public SupportDocDtls mapRow(ResultSet rs, int arg1)
				throws SQLException {
			SupportDocDtls rec = new SupportDocDtls();
			rec.setFileName(rs.getString("FILE_NAME"));
			rec.setRefId(rs.getString("REF_ID"));
			rec.setTimest(rs.getDate("INSERT_DT"));
			rec.setUser(rs.getString("EMAIL"));
			rec.setCateg(rs.getString("CATEGORY"));
			return rec;
		}
		
	}
}

