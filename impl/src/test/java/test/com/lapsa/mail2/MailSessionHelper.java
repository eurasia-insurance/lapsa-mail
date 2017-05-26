package test.com.lapsa.mail2;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.lapsa.mail.impl.MailSessionCustomProperties;

public final class MailSessionHelper {

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

    public static final Authenticator AUTHENTIFICATOR;
    static {
	AUTHENTIFICATOR = new Authenticator() {
	    public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(MAIL_TEST_USER, MAIL_TEST_PASSWORD);
	    }
	};
    }

    public static final Properties PROPERTIES;
    static {
	PROPERTIES = new Properties();

	PROPERTIES.put("mail.debug", MAIL_DEBUG);
	PROPERTIES.put("mail.from", MAIL_TEST_FROM_ADDRESS);

	PROPERTIES.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
	PROPERTIES.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".class", MAIL_TRANSPORT_IMPLEMENTATION_CLASS);
	PROPERTIES.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".auth", MAIL_TRANSPORT_TLS);
	PROPERTIES.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".host", MAIL_TRANSPORT_HOST);
	PROPERTIES.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".port", MAIL_TRANSPORT_PORT);
	PROPERTIES.put("mail." + MAIL_TRANSPORT_PROTOCOL + ".starttls.enable", MAIL_TRANSPORT_TLS);

	PROPERTIES.put("mail.store.protocol", MAIL_STORE_PROTOCOL);
	PROPERTIES.put("mail." + MAIL_STORE_PROTOCOL + ".class", MAIL_STORE_IMPLEMENTATION_CLASS);
	PROPERTIES.put("mail." + MAIL_STORE_PROTOCOL + ".host", MAIL_STORE_HOST);
	PROPERTIES.put("mail." + MAIL_STORE_PROTOCOL + ".port", MAIL_STORE_PORT);
	PROPERTIES.put("mail." + MAIL_STORE_PROTOCOL + ".starttls.enable", MAIL_STORE_TLS);

	PROPERTIES.put(MailSessionCustomProperties.MAIL_USER, MAIL_TEST_USER);
	PROPERTIES.put(MailSessionCustomProperties.MAIL_PASSWORD, MAIL_TEST_PASSWORD);
	PROPERTIES.put(MailSessionCustomProperties.MAIL_AUTHENTIFICATOR_OBJECT, AUTHENTIFICATOR);
    }

    public static final Session SESSION;
    static {
	SESSION = Session.getDefaultInstance(PROPERTIES, AUTHENTIFICATOR);
    }
}
