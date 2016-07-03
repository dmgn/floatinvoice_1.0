package com.floatinvoice.business.dao;

import java.util.List;
import java.util.Map;

public interface OrgReadDao {

	Map<String, Object> findOrgId( String acronym );

	List<Integer> findAllFinancierOrgIds();
	
	Integer findUserId(String userEmail);
	
	Map<String, Object> findOrgAndUserId( String userEmail );
	
	Integer findOrgIdByEmail(String userEmail);
	
	List<Integer> findAllNBFCS();
	
}
