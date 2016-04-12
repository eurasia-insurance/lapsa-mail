package com.lapsa.mailutil.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailSessionFactory {

    public static final String MAIL_TEST_USER = "lapsa.test@gmail.com";
    public static final String MAIL_TEST_PASSWORD = "SuperPuper2016";
    public static final String MAIL_TEST_RECIPIENT_ADDRESS = MAIL_TEST_USER;

    public static final String MAIL_SMTPS_HOST = "smtp.gmail.com";
    private static final String MAIL_SMTPS_PORT = "465";
    private static final Object MAIL_IMAPS_HOST = "imap.gmail.com";
    private static final String MAIL_IMAPS_PORT = "993";

    public static final String MAIL_TEST_FROM_ADDRESS = MAIL_TEST_USER;
    public static final String MAIL_TEST_TO_ADDRESS = MAIL_TEST_USER;

    public static Session createSession() {
	Properties prop = new Properties();

	prop.put("mail.debug", true);
	prop.put("mail.from", MAIL_TEST_FROM_ADDRESS);

	prop.put("mail.transport.protocol", "smtps");
	prop.put("mail.smtps.class", "com.sun.mail.smtp.SMTPSSLTransport");
	prop.put("mail.smtps.auth", "true");
	prop.put("mail.smtps.host", MAIL_SMTPS_HOST);
	prop.put("mail.smtps.port", MAIL_SMTPS_PORT);
	prop.put("mail.smtps.starttls.enable", "true");

	prop.put("mail.store.protocol", "imaps");
	prop.put("mail.imaps.class", "com.sun.mail.imap.IMAPSSLStore");
	prop.put("mail.imaps.host", MAIL_IMAPS_HOST);
	prop.put("mail.imaps.port", MAIL_IMAPS_PORT);
	prop.put("mail.imaps.starttls.enable", "true");

	Authenticator a = new Authenticator() {
	    public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(MAIL_TEST_USER, MAIL_TEST_PASSWORD);
	    }
	};
	Session session = Session.getInstance(prop, a);
	return session;
    }
}
