package com.metrobank.mail.test;

import static org.junit.Assert.assertNotNull;

import java.util.*;

import javax.mail.*;

import org.junit.*;

import com.lapsa.mailhelper.*;

public class BasicTestCase {

    private static final String MAIL_TEST_HOST = "almaty-mb01.theeurasia.local";
    private static final String MAIL_TEST_PASSWORD = "SuperPuper2016";
    private static final String MAIL_TEST_USER = "eurasia-policy";

    private static final String MAIL_TEST_FROM_ADDRESS = "junit@theeurasia.local";
    private static final String MAIL_TEST_RECIPIENT_ADDRESS = "vadim.isaev@theeurasia.kz";

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	Properties prop = new Properties();
	prop.put("mail.smtp.host", MAIL_TEST_HOST);
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
    public void testCreateFactory() throws MailHelperException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	assertNotNull(mhf);
    }

    @Test
    public void testGetHelper() throws MailHelperException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	assertNotNull(mh);
    }

    @Test
    public void testCreateSender() throws MailHelperException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	MailSender ms = mh.createSender();
	assertNotNull(ms);
    }

    @Test
    public void testCreateBuilder() throws MailHelperException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	MailMessageBuilder mmb = mh.createBuilder();
	assertNotNull(mmb);
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyRecipientsException() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	MailMessagePart part = mmb.createTextPart("Test message");
	message.addPart(part);
	message.setSubject("Test message");
	MailSender sender = mh.createSender();
	sender.send(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyBodyException() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("Test message");
	MailSender sender = mh.createSender();
	sender.send(message);
    }

    @Test
    public void testSend() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	MailSender sender = mh.createSender();
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("Test message");
	message.addPart(mmb.createTextPart("Test message"));
	sender.send(message);
    }

    @Test
    public void testSendAlwaysCopyTo() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailService mh = mhf.getHelper(session);
	MailSender sender = mh.createSender();
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	message.setSubject("Test message");
	message.addPart(mmb.createTextPart("Test message"));
	sender.setAlwaysBlindCopyTo(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	sender.send(message);
    }

}
