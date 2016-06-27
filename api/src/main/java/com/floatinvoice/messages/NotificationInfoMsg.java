package com.floatinvoice.messages;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NotificationInfo")
@XmlAccessorType(value=XmlAccessType.PROPERTY)
public class NotificationInfoMsg extends BaseMsg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="notificationId")
	private int notificationId;
	
	@XmlElement(name="recipientOrgId")
	private int recipientOrgId;
	
	@XmlElement(name="enquiryId")
	private int enquiryId;

	@XmlElement(name="refId")
	private String refId;
	
	@XmlElement(name="emailSentTS")
	private Date emailSentTS;
	
	@XmlElement(name="email")
	private String email;
	
	@XmlElement(name="enqFlag")
	private int enqFlag;

	@XmlElement(name="retry")
	private int retry;
	
	
	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public int getRecipientOrgId() {
		return recipientOrgId;
	}

	public void setRecipientOrgId(int recipientOrgId) {
		this.recipientOrgId = recipientOrgId;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Date getEmailSentTS() {
		return emailSentTS;
	}

	public void setEmailSentTS(Date emailSentTS) {
		this.emailSentTS = emailSentTS;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnqFlag() {
		return enqFlag;
	}

	public void setEnqFlag(int enqFlag) {
		this.enqFlag = enqFlag;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}
	
	
}
