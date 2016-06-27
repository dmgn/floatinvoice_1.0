package com.floatinvoice.messages;

import java.util.Date;

public class EnquiryFormMsg extends BaseMsg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contactName;
	
	private String email;
	
	private String phone;
	
	private String yrsInBusiness;
	
	private String industryType;
	
	private String location;
	
	private String productType;

	private String designation;
	
	private int source;
	
	private int enqStatus;
	
	private Date enqDate;
	
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYrsInBusiness() {
		return yrsInBusiness;
	}

	public void setYrsInBusiness(String yrsInBusiness) {
		this.yrsInBusiness = yrsInBusiness;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}


	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getEnqStatus() {
		return enqStatus;
	}

	public void setEnqStatus(int enqStatus) {
		this.enqStatus = enqStatus;
	}

	public Date getEnqDate() {
		return enqDate;
	}

	public void setEnqDate(Date enqDate) {
		this.enqDate = enqDate;
	}
	
	
}
