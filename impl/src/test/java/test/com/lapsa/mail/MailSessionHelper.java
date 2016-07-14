package test.com.lapsa.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailSessionHelper {

    public static final boolean MAIL_DEBUG = true;

    public static final String MAIL_TEST_USER = "lapsa.test@gmail.com";
    public static final String MAIL_TEST_PASSWORD = "SuperPuper2016";
    public static final String MAIL_TEST_RECIPIENT_ADDRESS = MAIL_TEST_USER;
    public static final String MAIL_TEST_FROM_ADDRESS = MAIL_TEST_USER;
    public static final String MAIL_TEST_TO_ADDRESS = MAIL_TEST_USER;

    public static final String MAIL_TRANSPORT_PROTOCOL = "smtps";
    public static final String MAIL_TRANSPORT_IMPLEMENTATION_CLASS = "com.sun.mail.smtp.SMTPSSLTransport";
    public static final String MAIL_TRANSPORT_HOST = "smtp.gmail.com";
    public static final String MAIL_TRANSPORT_PORT = "465";
    public static final String MAIL_TRANSPORT_TLS = "true";

    public static final String MAIL_STORE_PROTOCOL = "imaps";
    public static final String MAIL_STORE_IMPLEMENTATION_CLASS = "com.sun.mail.imap.IMAPSSLStore";
    public static final Object MAIL_STORE_HOST = "imap.gmail.com";
    public static final String MAIL_STORE_PORT = "993";
    public static final String MAIL_STORE_TLS = "true";

    public static Session createDefaultSession() {
	return Session.getInstance(createDefaultProperties(), createDefaultAuthenticator());
    }

    public static Authenticator createDefaultAuthenticator() {
	Authenticator a = new Authenticator() {
	    public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(MAIL_TEST_USER, MAIL_TEST_PASSWORD);
	    }
	};
	return a;
    }

    public static Properties createDefaultProperties() {
	final Properties prop = new Properties();

	prop.put("mail.debug", MAIL_DEBUG);
	prop.put("mail.from", MAIL_TEST_FROM_ADDRESS);

	prop.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
	prop.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".class", MAIL_TRANSPORT_IMPLEMENTATION_CLASS);
	prop.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".auth", MAIL_TRANSPORT_TLS);
	prop.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".host", MAIL_TRANSPORT_HOST);
	prop.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".port", MAIL_TRANSPORT_PORT);
	prop.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".starttls.enable", MAIL_TRANSPORT_TLS);

	prop.put("mail.store.protocol", MAIL_STORE_PROTOCOL);
	prop.put("mail." + MAIL_STORE_PROTOCOL + ".class", MAIL_STORE_IMPLEMENTATION_CLASS);
	prop.put("mail." + MAIL_STORE_PROTOCOL + ".host", MAIL_STORE_HOST);
	prop.put("mail." + MAIL_STORE_PROTOCOL + ".port", MAIL_STORE_PORT);
	prop.put("mail." + MAIL_STORE_PROTOCOL + ".starttls.enable", MAIL_STORE_TLS);

	return prop;
    }
}
