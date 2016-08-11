package com.floatinvoice.messages;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SupportDocDtls")
@XmlAccessorType(value=XmlAccessType.PROPERTY)
public class SupportDocDtls extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="fileName")
	String fileName;

	@XmlElement(name="refId")
	String refId;

	@XmlElement(name="timest")
	Date timest;

	@XmlElement(name="status")
	String status;
	
	@XmlElement(name="user")	
	String user;

	@XmlElement(name="categ")	
	String categ;
	
	@XmlElement(name="acro")	
	String acro;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Date getTimest() {
		return timest;
	}

	public void setTimest(Date timest) {
		this.timest = timest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCateg() {
		return categ;
	}

	public void setCateg(String categ) {
		this.categ = categ;
	}

	public String getAcro() {
		return acro;
	}

	public void setAcro(String acro) {
		this.acro = acro;
	}
	
	
}
