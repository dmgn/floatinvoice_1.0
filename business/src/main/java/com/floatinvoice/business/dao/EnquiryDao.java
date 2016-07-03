package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.EnquiryFormMsg;

public interface EnquiryDao {

	List<EnquiryFormMsg> viewEnquiries(int enqStatus, String orgType);
	
	int updateEnquiry(int enqStatus, String refId);
	
	EnquiryFormMsg findOneEnquiry(String refId);
	
	void saveEnquiryNotifications(EnquiryFormMsg enquiry, int orgId);
	
	List<EnquiryFormMsg> viewEnquiryNotifications(int orgId);
	
	int mapEnquiryToOrgSetup(String enquiryRefId, int enquiryId, int orgId, int userId);
}
