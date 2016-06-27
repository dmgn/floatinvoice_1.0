/**
 * 
 */
package com.floatinvoice.business.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author gnaik
 *
 */
public class JdbcOrgReadDao implements OrgReadDao {

	/* (non-Javadoc)
	 * @see com.floatinvoice.business.dao.OrgReadDao#findSMEOrgId(java.lang.String)
	 * 
	 * 
	 */
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public JdbcOrgReadDao(){
		
	}
	
	public JdbcOrgReadDao( DataSource dataSource){
		 jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
	}
	
	@Override
	public Map<String, Object> findOrgId( String acronym ){
		Map<String, Object> result;
		final String sql = "select company_id,org_type from ORGANIZATION_INFO oi where oi.acronym=:acronym";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("acronym",acronym);
		result = jdbcTemplate.queryForObject(sql, params, new ColumnMapRowMapper());
		return result;
	}

	@Override
	public List<Integer> findAllFinancierOrgIds(){
		List<Map<String, Object>> queryResultSet;
		final String sql = "select company_id from ORGANIZATION_INFO oi where oi.org_type='BANK'";
		queryResultSet = jdbcTemplate.query(sql, new ColumnMapRowMapper());
		List<Integer> result = new ArrayList<>();
		for ( Map<String, Object> map : queryResultSet ){
			result.add((Integer)map.get("company_id"));
		}
		return result;
	}

	@Override
	public Integer findUserId(String userEmail) {
		final String sql = "SELECT USER_ID FROM CLIENT_LOGIN_INFO WHERE EMAIL = :email";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("email", userEmail);
		Integer userId = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
		return userId;
	}
	
	@Override
	public Integer findOrgIdByEmail(String userEmail) {
		final String sql = "SELECT O.COMPANY_ID FROM ORGANIZATION_INFO O "
				+ " JOIN USER_ORGANIZATION_MAP UOM "
				+ " ON O.COMPANY_ID = UOM.COMPANY_ID "
				+ " JOIN CLIENT_LOGIN_INFO CLI "
				+ " ON CLI.USER_ID = UOM.USER_ID "
				+ " WHERE CLI.EMAIL = :email";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("email", userEmail);
		Integer orgId = jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
		return orgId;
	}

	@Override
	public Map<String, Object> findOrgAndUserId(String userEmail) {
		final String sql = "SELECT UOM.USER_ID, UOM.COMPANY_ID FROM CLIENT_LOGIN_INFO CLI "
				+ " JOIN USER_ORGANIZATION_MAP UOM"
				+ " ON CLI.USER_ID = UOM.USER_ID"
				+ " WHERE CLI.EMAIL = :email";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("email", userEmail);
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, paramMap);
		return result;
	}
}
