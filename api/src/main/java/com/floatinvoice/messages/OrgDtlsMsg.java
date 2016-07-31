package com.floatinvoice.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="OrgDtls")
@XmlAccessorType(value=XmlAccessType.PROPERTY)
public class OrgDtlsMsg extends BaseMsg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="acro")
	private String acro;
	
	@XmlElement(name="orgName")
	private String orgName;

	
	public String getAcro() {
		return acro;
	}

	public void setAcro(String acro) {
		this.acro = acro;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}
