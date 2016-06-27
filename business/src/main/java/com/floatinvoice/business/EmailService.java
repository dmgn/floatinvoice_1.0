package com.floatinvoice.business;

public interface EmailService {

	void sendEmail( String subject, String recipientEmailAddress, StringBuffer bodyText );
}
