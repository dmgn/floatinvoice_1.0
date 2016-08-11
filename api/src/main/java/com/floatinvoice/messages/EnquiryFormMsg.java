package com.floatinvoice.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	private String enqStatus;
	
	private Date enqDate;
	
	private int enqId;
	
	private int userId;
	
	private int companyId;
	
	private String acro;
	
	private List<SupportDocDtls> supportDocs = new ArrayList<>();
	
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


	public String getEnqStatus() {
		return enqStatus;
	}

	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

	public Date getEnqDate() {
		return enqDate;
	}

	public void setEnqDate(Date enqDate) {
		this.enqDate = enqDate;
	}

	public int getEnqId() {
		return enqId;
	}

	public void setEnqId(int enqId) {
		this.enqId = enqId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public List<SupportDocDtls> getSupportDocs() {
		return supportDocs;
	}

	public void setSupportDocs(List<SupportDocDtls> supportDocs) {
		this.supportDocs = supportDocs;
	}

	public String getAcro() {
		return acro;
	}

	public void setAcro(String acro) {
		this.acro = acro;
	}

		
}
