package com.metrobank.mail.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lapsa.mailutil.InvalidMessageException;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailFactory;
import com.lapsa.mailutil.MailMessage;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailSender;
import com.lapsa.mailutil.MailService;

public class BasicTestCase {

    private static final String MAIL_TEST_USER = "lapsa.test@gmail.com";
    private static final String MAIL_TEST_PASSWORD = "SuperPuper2016";

    private static final String MAIL_TEST_HOST = "smtp.gmail.com";

    private static final String MAIL_TEST_FROM_ADDRESS = MAIL_TEST_USER;
    private static final String MAIL_TEST_RECIPIENT_ADDRESS = MAIL_TEST_USER;

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	Properties prop = new Properties();
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.starttls.enable", "true");

	prop.put("mail.smtp.host", MAIL_TEST_HOST);
	prop.put("mail.smtp.port", "587");
	prop.put("mail.from", MAIL_TEST_FROM_ADDRESS);
	prop.put("mail.debug", true);
	Authenticator a = new Authenticator() {
	    public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(MAIL_TEST_USER, MAIL_TEST_PASSWORD);
	    }
	};
	session = Session.getInstance(prop, a);
    }

    @Test
    public void testCreateFactory() throws MailException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	assertNotNull(mhf);
    }

    @Test
    public void testgetService() throws MailException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	assertNotNull(mh);
    }

    @Test
    public void testCreateSender() throws MailException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	MailSender ms = mh.createSender();
	assertNotNull(ms);
    }

    @Test
    public void testCreateBuilder() throws MailException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	MailMessageBuilder mmb = mh.createBuilder();
	assertNotNull(mmb);
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyRecipientsException() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	MailMessagePart part = mmb.createTextPart("Test message");
	message.addPart(part);
	message.setSubject("Test message");
	MailSender sender = mh.createSender();
	sender.send(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyBodyException() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("Test message");
	MailSender sender = mh.createSender();
	sender.send(message);
    }

    @Test
    public void testSend() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	MailSender sender = mh.createSender();
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("testSend");
	message.addPart(mmb.createTextPart("testSend"));
	sender.send(message);
    }

    @Test
    public void testSendAlwaysCopyTo() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	MailSender sender = mh.createSender();
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("testSendAlwaysCopyTo");
	message.addPart(mmb.createTextPart("testSendAlwaysCopyTo"));
	sender.setAlwaysBlindCopyTo(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	sender.send(message);
    }

    @Test
    public void testSendImage_InputStream() throws MailException, IOException, InvalidMessageException {
	InputStream is = null;
	try {
	    MailFactory mf = MailFactory.getDefaultMailFactory();
	    MailService ms = mf.getService(session);
	    MailMessageBuilder builder = ms.createBuilder();
	    MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_InputStream");
	    is = testFileInputStream();
	    message.addPart(builder.createStreamPart("PICTURE", "image/jpeg", is));
	    MailSender sender = ms.createSender();
	    sender.send(message);
	} finally {
	    if (is != null)
		is.close();
	}
    }

    @Test
    public void testSendImage_Bytes() throws MailException, IOException, InvalidMessageException {
	MailFactory mf = MailFactory.getDefaultMailFactory();
	MailService ms = mf.getService(session);
	MailMessageBuilder builder = ms.createBuilder();
	MailMessage message = builder.createMessage();
	message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("testSendImage_Bytes");
	message.addPart(builder.createByteArrayPart("PICTURE", "image/jpeg", testFileBytes()));
	MailSender sender = ms.createSender();
	sender.send(message);
    }

    private InputStream testFileInputStream() {
	InputStream is = this.getClass().getClassLoader().getResourceAsStream("policy_sample.jpg");
	return is;
    }

    private byte[] testFileBytes() throws IOException {
	InputStream is = null;
	ByteArrayOutputStream baos = null;
	try {
	    is = testFileInputStream();
	    byte[] buff = new byte[256];
	    int readed;
	    baos = new ByteArrayOutputStream();
	    while ((readed = is.read(buff)) != -1)
		baos.write(buff, 0, readed);
	    return baos.toByteArray();
	} finally {
	    if (baos != null)
		try {
		    baos.close();
		} catch (IOException e) {
		}
	    if (is != null)
		try {
		    baos.close();
		} catch (IOException e) {
		}
	}
    }

}
