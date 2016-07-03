package com.floatinvoice.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import com.floatinvoice.common.UUIDGenerator;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;

public class JdbcFileServiceDao implements FileServiceDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private OrgReadDao orgReadDao;
	private LobHandler lobHandler;
	final static String sql = "INSERT INTO LENDER_AGREEMENT_DOC (FILE_NAME, AGREEMENT, INSERT_DT, UPDATE_DT, COMPANY_ID, REF_ID, REQUEST_ID, SOURCE_APP) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	
	public JdbcFileServiceDao(DataSource dataSource, OrgReadDao orgReadDao, LobHandler lobHandler){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.orgReadDao = orgReadDao;
		this.lobHandler = lobHandler;
	}
	
	@Override
	public byte[] download(String refId) {
	
		final String sql = "SELECT FILE_BYTES FROM FILE_STORE WHERE REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, paramMap);
		return (byte[]) result.get("FILE_BYTES");
	}

	@Override
	public BaseMsg uploadLenderAgreement(final UploadMessage msg) throws Exception {
		Map<String, Object> orgInfo = orgReadDao.findOrgId(msg.getAcronym());
		final int orgId = (int) orgInfo.get("COMPANY_ID");
		final byte [] bytes = msg.getFile().getBytes();
		final LobCreator lobCreator = lobHandler.getLobCreator();
		jdbcTemplate.getJdbcOperations().update( new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				final PreparedStatement ps = conn.prepareStatement(sql);
				try {
					ps.setString(1, msg.getFileName());
					lobCreator.setBlobAsBytes(ps, 2, bytes);
					ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
					ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
					ps.setInt(5, orgId);
					ps.setString(6, UUIDGenerator.newRefId());
					ps.setString(7, UUID.randomUUID().toString());
					ps.setInt(8, 0);
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
	public byte[] downloadLenderAgreement(String lenderAcronym, String refId) {
		Map<String, Object> map = orgReadDao.findOrgId(lenderAcronym);
		int orgId = (Integer) map.get("company_id");
		final String sql = "SELECT AGREEMENT FROM LENDER_AGREEMENT_DOC WHERE COMPANY_ID = :orgId AND "
				+ " REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		paramMap.addValue("orgId", orgId);
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, paramMap);
		return (byte[]) result.get("AGREEMENT");
	}

	@Override
	public byte[] downloadSupportDocs(String refId) {
		
		final String sql = "SELECT FILE_BYTES FROM DOCS_STORE WHERE REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, paramMap);
		return (byte[]) result.get("FILE_BYTES");
	}

	@Override
	public List<SupportDocDtls> summarySupportDocs(int companyId, int userId) {
		
		final String sql = "SELECT DS.FILE_NAME, DS.REF_ID, DS.INSERT_DT, DS.CATEGORY, CLI.EMAIL FROM DOCS_STORE DS"
				+ " JOIN CLIENT_LOGIN_INFO CLI"
				+ " ON CLI.USER_ID = DS.USER_ID"
				+ " WHERE DS.COMPANY_ID = :companyId AND DS.USER_ID = :userId";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("companyId", companyId);
		paramMap.put("userId", userId);
		List<SupportDocDtls> list = jdbcTemplate.query(sql, paramMap, new SupportDocRowMapper());
		return list;
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
