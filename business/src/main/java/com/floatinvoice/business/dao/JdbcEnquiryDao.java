package com.floatinvoice.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.floatinvoice.common.EnquiryStatusEnum;
import com.floatinvoice.common.IndustryTypeEnum;
import com.floatinvoice.common.OrgType;
import com.floatinvoice.common.ProductTypeEnum;
import com.floatinvoice.common.UUIDGenerator;
import com.floatinvoice.common.UserContext;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.SupportDocDtls;

public class JdbcEnquiryDao implements EnquiryDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate ficoreJdbcTemplate;
	
	public JdbcEnquiryDao(){
		
	}
	
	public JdbcEnquiryDao(DataSource dataSource, DataSource ficoreDataSource){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.ficoreJdbcTemplate = new NamedParameterJdbcTemplate(ficoreDataSource);
	}


	@Override
	public EnquiryFormMsg viewStagedEnquiry(int enqStatus, String refId, String companyId) {
		String sql = "SELECT EI.ENQUIRY_ID, EI.REF_ID, EI.CONTACT_NAME, EI.EMAIL, EI.PHONE, EI.YRS_IN_BUSINESS, "
				+ " EI.INDUSTRY, EI.LOCATION, EI.PRODUCT_TYPE, EI.INSERT_DT, EI.DESIGNATION, EI.SOURCE, EI.ENQUIRY_STATUS, EOM.COMPANY_ID "
				+ " FROM ENQUIRY_INFO EI "
				+ " JOIN ENQUIRY_ORG_MAP EOM "
				+ " ON EI.ENQUIRY_ID = EOM.ENQUIRY_ID "
				+ " WHERE EI.REF_ID = :refId "
				+ " AND EI.ENQUIRY_STATUS = :enqStatus";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("enqStatus", enqStatus);
		paramMap.addValue("refId", refId);
		EnquiryFormMsg result = jdbcTemplate.queryForObject(sql, paramMap, new EnquiryRowMapper());
		String supportDocsSql = "SELECT DS.CATEGORY, DS.REF_ID, DS.FILE_NAME, DS.INSERT_DT, CLI.EMAIL, OI.ACRONYM FROM DOCS_STORE DS "
				+ " JOIN CLIENT_LOGIN_INFO CLI "
				+ " ON DS.USER_ID = CLI.USER_ID "
				+ " JOIN ORGANIZATION_INFO OI "
				+ " ON OI.COMPANY_ID = DS.COMPANY_ID"
				+ " WHERE DS.COMPANY_ID = :companyId";
		MapSqlParameterSource paramMapsupportDocs = new MapSqlParameterSource();
		paramMapsupportDocs.addValue("companyId", companyId);
		List<SupportDocDtls> supportDocs = ficoreJdbcTemplate.query(supportDocsSql, paramMapsupportDocs, new StagedEnquirySupportDocsDtlsRowMapper());
		result.setSupportDocs(supportDocs);
		return result;
	}
	
	@Override
	public List<EnquiryFormMsg> viewEnquiries(int enqStatus, String orgType) {		
		String sql = null;
		if (OrgType.ADMIN.getText().equalsIgnoreCase(orgType)){
			sql = "SELECT EI.ENQUIRY_ID, EI.REF_ID, EI.CONTACT_NAME, EI.EMAIL, EI.PHONE, EI.YRS_IN_BUSINESS, "
					+ " EI.INDUSTRY, EI.LOCATION, EI.PRODUCT_TYPE, EI.INSERT_DT, EI.DESIGNATION, EI.SOURCE, EI.ENQUIRY_STATUS, EOM.COMPANY_ID, NULL AS ACRO "
					+ " FROM ENQUIRY_INFO EI "
					+ " LEFT JOIN ENQUIRY_ORG_MAP EOM "
					+ " ON EI.ENQUIRY_ID = EOM.ENQUIRY_ID "
					+ " WHERE ENQUIRY_STATUS = :enqStatus";
		}else{
			sql = "SELECT EI.ENQUIRY_ID, EI.REF_ID, EI.CONTACT_NAME, EI.EMAIL, EI.PHONE, EI.YRS_IN_BUSINESS, "
					+ " EI.INDUSTRY, EI.LOCATION, EI.PRODUCT_TYPE, EI.INSERT_DT, EI.DESIGNATION, EI.SOURCE, EI.ENQUIRY_STATUS, EOM.COMPANY_ID, NULL AS ACRO "
					+ " FROM ENQUIRY_INFO EI "
					+ " LEFT JOIN ENQUIRY_ORG_MAP EOM "
					+ " ON EI.ENQUIRY_ID = EOM.ENQUIRY_ID "
					+ " WHERE ENQUIRY_STATUS = :enqStatus AND EMAIL = :email";
		}
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("enqStatus", enqStatus);
		paramMap.addValue("email", UserContext.getUserName());
		List<EnquiryFormMsg> lst = jdbcTemplate.query(sql, paramMap, new EnquiryRowMapper());
		return lst;
	}

	
	private class StagedEnquirySupportDocsDtlsRowMapper implements RowMapper<SupportDocDtls>{

		@Override
		public SupportDocDtls mapRow(ResultSet rs, int arg1)
				throws SQLException {
			SupportDocDtls row = new SupportDocDtls();
			row.setCateg(rs.getString("CATEGORY"));
			row.setRefId(rs.getString("REF_ID"));
			row.setFileName(rs.getString("FILE_NAME"));
			row.setTimest(rs.getDate("INSERT_DT"));
			row.setUser(rs.getString("EMAIL"));
			row.setAcro(rs.getString("ACRONYM"));
			return row;
		}
		
		
	}

	private class EnquiryRowMapper implements RowMapper<EnquiryFormMsg>{

		@Override
		public EnquiryFormMsg mapRow(ResultSet rs, int arg1)
				throws SQLException {
			EnquiryFormMsg row = new EnquiryFormMsg();
			row.setContactName(rs.getString("CONTACT_NAME"));
			row.setDesignation(rs.getString("DESIGNATION"));
			row.setEmail(rs.getString("EMAIL"));
			row.setIndustryType(IndustryTypeEnum.get(rs.getInt("INDUSTRY")).getText());
			row.setLocation(rs.getString("LOCATION"));
			row.setPhone(rs.getString("PHONE"));
			row.setProductType(ProductTypeEnum.get(rs.getInt("PRODUCT_TYPE")).getText());
			row.setYrsInBusiness(rs.getString("YRS_IN_BUSINESS"));
			row.setRefId(rs.getString("REF_ID"));
			row.setSource(rs.getInt("SOURCE"));
			row.setEnqStatus(EnquiryStatusEnum.get(rs.getInt("ENQUIRY_STATUS")).getText());
			row.setEnqDate(rs.getDate("INSERT_DT"));
			row.setEnqId(rs.getInt("ENQUIRY_ID"));
			row.setCompanyId(rs.getInt("COMPANY_ID"));
			
			return row;
		}
		
		
	}
	
	@Override
	public int updateEnquiry(int enqStatus, String refId) {
		int rowsUpdated = 0;
		final String sql = "UPDATE ENQUIRY_INFO EI SET EI.ENQUIRY_STATUS = :enqStatus WHERE REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("enqStatus", enqStatus);
		paramMap.addValue("refId", refId);
		rowsUpdated = jdbcTemplate.update(sql, paramMap);
		return rowsUpdated;
	}

	@Override
	public EnquiryFormMsg findOneEnquiry(String refId) {
		final String sql = "SELECT EI.ENQUIRY_ID, EI.REF_ID, "
							+ " EI.CONTACT_NAME, EI.EMAIL, EI.PHONE, EI.YRS_IN_BUSINESS, "
							+ " EI.INDUSTRY, EI.LOCATION, EI.PRODUCT_TYPE, EI.INSERT_DT, "
							+ " EI.DESIGNATION, EI.SOURCE, EI.ENQUIRY_STATUS, NULL AS COMPANY_ID "
							+ " FROM ENQUIRY_INFO EI WHERE REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		EnquiryFormMsg enquiry = jdbcTemplate.queryForObject(sql, paramMap, new EnquiryRowMapper());
		return enquiry;
	}

	@Override
	public void saveEnquiryNotifications(EnquiryFormMsg enquiry, int orgId) {
		int rowsInserted = 0;
		final String sql = "INSERT INTO THIRDPARTY_NOTIFICATION_QUEUE(RECIPIENT_ORG_ID, REF_ID, EMAIL, ENQ_FLAG, RETRY_ATTEMPT, ENQ_REF_ID, ENQUIRY_ID)  "
				+ "VALUES (:orgId, :refId, :email, :enqFlag, :retry, :enqRefId, :enquiryId)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("orgId", orgId);
		params.addValue("refId", UUIDGenerator.newRefId());
		params.addValue("email", enquiry.getEmail());
		params.addValue("enqFlag", 0);
		params.addValue("retry", 0);
		params.addValue("enqRefId", enquiry.getRefId());
		params.addValue("enquiryId", enquiry.getEnqId());
		rowsInserted = jdbcTemplate.update(sql, params);		
	}

	@Override
	public List<EnquiryFormMsg> viewEnquiryNotifications(int orgId) {
		final String sql = "SELECT EOM.COMPANY_ID, EOM.USER_ID, EI.REF_ID, EI.CONTACT_NAME, EI.EMAIL, EI.PHONE, EI.LOCATION, EI.INSERT_DT, EI.YRS_IN_BUSINESS FROM ENQUIRY_INFO EI "
				+ "JOIN  THIRDPARTY_NOTIFICATION_QUEUE TNQ "
				+ "ON EI.ENQUIRY_ID = TNQ.ENQUIRY_ID "
				+ "JOIN ENQUIRY_ORG_MAP EOM "
				+ "ON EI.ENQUIRY_ID = EOM.ENQUIRY_ID  "
				+ "WHERE TNQ.RECIPIENT_ORG_ID = :orgId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("orgId", orgId);
		List<EnquiryFormMsg> enquiryList = jdbcTemplate.query(sql, paramMap, new EnquiryNotificationRowMapper());
		return enquiryList;
	}
	
	private class EnquiryNotificationRowMapper implements RowMapper<EnquiryFormMsg>{
		@Override
		public EnquiryFormMsg mapRow(ResultSet rs, int arg1)
				throws SQLException {
			EnquiryFormMsg row = new EnquiryFormMsg();
			row.setContactName(rs.getString("CONTACT_NAME"));
			row.setEmail(rs.getString("EMAIL"));
			row.setLocation(rs.getString("LOCATION"));
			row.setPhone(rs.getString("PHONE"));
			row.setYrsInBusiness(rs.getString("YRS_IN_BUSINESS"));
			row.setRefId(rs.getString("REF_ID"));
			row.setEnqDate(rs.getDate("INSERT_DT"));
			row.setCompanyId(rs.getInt("COMPANY_ID"));
			row.setUserId(rs.getInt("USER_ID"));
			return row;
		}
	}

	@Override
	public int mapEnquiryToOrgSetup(String enquiryRefId, int enquiryId, int companyId, int userId) {
		final String sql = "INSERT INTO ENQUIRY_ORG_MAP(COMPANY_ID, ENQUIRY_REF_ID, USER_ID, ENQUIRY_ID)  "
				+ "VALUES (:companyId, :enqRefId, :userId, :enquiryId)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("companyId", companyId);
		params.addValue("enqRefId", enquiryRefId);
		params.addValue("userId", userId);
		params.addValue("enquiryId", enquiryId);
		return jdbcTemplate.update(sql, params);		
		
	}


}
