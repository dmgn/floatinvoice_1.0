package com.floatinvoice.business;

import com.floatinvoice.business.dao.EnquiryDao;
import com.floatinvoice.common.EnquiryStatusEnum;
import com.floatinvoice.common.Utility;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.EnquiryFormMsg;
import com.floatinvoice.messages.ListMsg;
import com.floatinvoice.messages.RegistrationStep1SignInDtlsMsg;

public class EnquiryServiceImpl implements EnquiryService {
	
	private EnquiryDao enqDao;
	
	private EmailService emailService;
	
	private RegistrationService regService;
	
	public EnquiryServiceImpl(){
		
	}
	
	public EnquiryServiceImpl(EnquiryDao enqDao, EmailService emailService, RegistrationService regService){
		this.enqDao = enqDao;
		this.emailService = emailService;
		this.regService = regService;
	}
	
	@Override
	public ListMsg<EnquiryFormMsg> viewEnquiry(int enqStatus) {
		return new ListMsg<>(enqDao.viewEnquiries(enqStatus));
	}

	@Override
	public BaseMsg updateEnquiry(int enqStatus, String refId) {
		int result = enqDao.updateEnquiry(enqStatus, refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return response;
	}

	@Override
	public BaseMsg sendAcctSetupNotification(String email, String refId, String password) {
		//EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		int result = enqDao.updateEnquiry(EnquiryStatusEnum.PENDING.getCode(), refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			emailService.sendEmail("Float Invoice Account Setup", email, 
					new StringBuffer(String.format("Please login to http://54.88.19.112:8080/floatinvoice/loginpage using the credentials in the email."
							+ " Login Id is %s and Password is %s", email, password)));
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return response;
	}

	@Override
	public BaseMsg notifyFloatInvoice(String user, String acronym, String refId) {
		EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		int result = enqDao.updateEnquiry(EnquiryStatusEnum.STAGED.getCode(), refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			emailService.sendEmail("Documents Uploaded - " + enquiry.getContactName(), "support@floatinvoice.com", new StringBuffer("All requested documents are uploaded."));
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return null;
	}

	@Override
	public BaseMsg sendThirdPartyNotification(String refId) {
		EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		int result = enqDao.updateEnquiry(EnquiryStatusEnum.RELEASED.getCode(), refId);
		BaseMsg response = new BaseMsg();
		if(result > 0){
			emailService.sendEmail("Prospect from Float Invoice", "askforgautam@gmail.com", new StringBuffer("Please login to floatinvoice and check out the prospect details.")); // change the email
			response.addInfoMsg("Enquiry Updated Successfully", 0);
		}else{
			response.addInfoMsg("Enquiry Update Failed", -1);
		}
		return response;
	}

	@Override
	public BaseMsg setupTempAcct(String refId) {
		EnquiryFormMsg enquiry = enqDao.findOneEnquiry(refId);
		RegistrationStep1SignInDtlsMsg signInMsg = new RegistrationStep1SignInDtlsMsg();
		signInMsg.setEmail(enquiry.getEmail());
		signInMsg.setPasswd(Utility.passwdString(8));
		signInMsg.setConfirmPasswd(Utility.passwdString(8));
		BaseMsg signInMsgResp = regService.registerSignInInfo(signInMsg);
		BaseMsg acctSetupResp = null;
		if(signInMsgResp.getSystemMessages().getInfo().size() > 0){
			acctSetupResp = sendAcctSetupNotification(enquiry.getEmail(), refId, signInMsg.getPasswd());
		}		
		return acctSetupResp;
	}

}
