package com.floatinvoice.business;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailServiceImpl implements EmailService {

	private EmailProperties emailProps;
	
	public EmailServiceImpl(){
		
	}
	
	public EmailServiceImpl(EmailProperties emailProps){
		this.emailProps = emailProps;
	}
	
	@Override
	public void sendEmail( String subject, String recipientEmailAddress, StringBuffer bodyText ) {
		Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", emailProps.getSmtpServer());
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.user", emailProps.getUser());
        properties.setProperty("mail.smtp.password", emailProps.getPassword());
        properties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() 
        {   @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {   return new PasswordAuthentication(emailProps.getFromEmailAddress(), emailProps.getPassword());
            }
        });
        try{   
        	MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailProps.getFromEmailAddress()));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(recipientEmailAddress));
            message.setSubject(subject);
            message.setText(bodyText.toString());
            Transport.send(message);
        } 
        catch (MessagingException e) 
        {   e.printStackTrace();
        }

	}

}
