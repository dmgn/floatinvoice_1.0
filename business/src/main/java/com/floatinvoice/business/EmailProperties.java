package com.floatinvoice.business;

public class EmailProperties {

	String smtpServer;
	
	String user;
	
	String password;
	
	String fromEmailAddress;

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}
	
	
}
