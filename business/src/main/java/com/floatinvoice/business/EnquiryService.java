package com.floatinvoice.business;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.ListMsg;

public interface EnquiryService {

	ListMsg<EnquiryFormMsg> viewEnquiry(int enqStatus);
	
	BaseMsg updateEnquiry(int enqStatus, String refId);

	BaseMsg sendAcctSetupNotification(String email, String refId, String password);
	
	BaseMsg sendThirdPartyNotification(String refId);
	
	BaseMsg notifyFloatInvoice(String user, String acronym, String refId);
	
	BaseMsg setupTempAcct(String refId);
}
