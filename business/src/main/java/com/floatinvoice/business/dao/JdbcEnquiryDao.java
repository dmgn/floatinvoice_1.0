package com.floatinvoice.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.floatinvoice.messages.EnquiryFormMsg;

public class JdbcEnquiryDao implements EnquiryDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcEnquiryDao(){
		
	}
	
	public JdbcEnquiryDao(DataSource dataSource){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<EnquiryFormMsg> viewEnquiries(int enqStatus) {		
		final String sql = "SELECT * FROM ENQUIRY_INFO WHERE ENQUIRY_STATUS = :enqStatus";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("enqStatus", enqStatus);
		List<EnquiryFormMsg> lst = jdbcTemplate.query(sql, paramMap, new EnquiryRowMapper());
		return lst;
	}

	
	

	private class EnquiryRowMapper implements RowMapper<EnquiryFormMsg>{

		@Override
		public EnquiryFormMsg mapRow(ResultSet rs, int arg1)
				throws SQLException {
			EnquiryFormMsg row = new EnquiryFormMsg();
			row.setContactName(rs.getString("CONTACT_NAME"));
			row.setDesignation(rs.getString("DESIGNATION"));
			row.setEmail(rs.getString("EMAIL"));
			row.setIndustryType(rs.getString("INDUSTRY"));
			row.setLocation(rs.getString("LOCATION"));
			row.setPhone(rs.getString("PHONE"));
			row.setProductType(rs.getString("PRODUCT_TYPE"));
			row.setYrsInBusiness(rs.getString("YRS_IN_BUSINESS"));
			row.setRefId(rs.getString("REF_ID"));
			row.setSource(rs.getInt("SOURCE"));
			row.setEnqStatus(rs.getInt("ENQUIRY_STATUS"));
			row.setEnqDate(rs.getDate("INSERT_DT"));
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
		final String sql = "SELECT * FROM ENQUIRY_INFO WHERE REF_ID = :refId";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("refId", refId);
		EnquiryFormMsg enquiry = jdbcTemplate.queryForObject(sql, paramMap, new EnquiryRowMapper());
		return enquiry;
	}
}
