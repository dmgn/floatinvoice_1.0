package com.floatinvoice.business.dao;

import java.util.List;

import com.floatinvoice.messages.EnquiryFormMsg;

public interface EnquiryDao {

	List<EnquiryFormMsg> viewEnquiries(int enqStatus);
	
	int updateEnquiry(int enqStatus, String refId);
	
	EnquiryFormMsg findOneEnquiry(String refId);
}
