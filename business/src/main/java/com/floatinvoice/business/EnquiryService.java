package com.floatinvoice.business;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.OrgDtlsMsg;

public interface EnquiryService {

	ListMsg<EnquiryFormMsg> viewEnquiry(int enqStatus, String orgType);
	
	BaseMsg updateEnquiry(int enqStatus, String refId);

	BaseMsg sendAcctSetupNotification(String email, String refId, String password);
	
	BaseMsg sendThirdPartyNotification(String refId);
	
	BaseMsg notifyFloatInvoice(String user, String acronym, String refId);
	
	BaseMsg setupTempAcct(OrgDtlsMsg orgDtls);
	
	ListMsg<EnquiryFormMsg> viewEnquiryNotifications(String acronym);

	void mapEnquiryToOrgSetup(String enquiryRefId, int enquiryId, int orgId, int userId);
	
	EnquiryFormMsg viewStagedEnquiry(int enqStatus, String refId, String companyId);
}