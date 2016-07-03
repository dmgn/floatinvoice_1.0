package com.floatinvoice.business;

import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ByteMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.SupportDocDtls;
import com.floatinvoice.messages.UploadMessage;

public interface FileService {

	ByteMsg download(String refId);
	
	ByteMsg downloadSupportDocs(String refId);

	ByteMsg downloadLenderAgreement(String lenderAcronym, String refId);
	
	BaseMsg uploadLenderAgreement( UploadMessage msg)  throws Exception;

	ListMsg<SupportDocDtls> summarySupportDocs(int companyId, int userId); 
}
