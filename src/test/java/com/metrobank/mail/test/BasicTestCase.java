package com.metrobank.mail.test;

import static org.junit.Assert.*;

import java.util.*;

import javax.mail.*;

import org.junit.*;

import com.metrobank.mail.helper.*;
import com.metrobank.mail.helper.impl.*;

public class BasicTestCase {

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.bank.ru");
	prop.put("mail.from", "junit@metrobank.ru");
	prop.put("mail.debug", true);
	Authenticator a = new Authenticator() {
	    // public PasswordAuthentication getPasswordAuthentication() {
	    // return new PasswordAuthentication("postmaster", "012549");
	    // }
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
	MailHelper mh = mhf.getHelper(session);
	assertNotNull(mh);
    }

    @Test
    public void testCreateSender() throws MailHelperException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailHelper mh = mhf.getHelper(session);
	MailSender ms = mh.createSender();
	assertNotNull(ms);
    }

    @Test
    public void testCreateBuilder() throws MailHelperException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailHelper mh = mhf.getHelper(session);
	MailMessageBuilder mmb = mh.createBuilder();
	assertNotNull(mmb);
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyRecipientsException() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailHelper mh = mhf.getHelper(session);
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
	MailHelper mh = mhf.getHelper(session);
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress("vadim.isaev@metrobank.ru"));
	message.setSubject("Test message");
	MailSender sender = mh.createSender();
	sender.send(message);
    }

    // @Test
    public void testSend() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailHelper mh = mhf.getHelper(session);
	MailSender sender = mh.createSender();
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress("vadim.isaev@metrobank.ru"));
	message.setSubject("Test message");
	message.addPart(mmb.createTextPart("Test message"));
	sender.send(message);
    }

    // @Test
    public void testSendAlwaysCopyTo() throws MailHelperException, InvalidMessageException {
	MailHelperFactory mhf = MailHelperFactory.getDefaultMailHelperFactory();
	MailHelper mh = mhf.getHelper(session);
	MailSender sender = mh.createSender();
	MailMessageBuilder mmb = mh.createBuilder();

	MailMessage message = mmb.createMessage();
	message.addTORecipient(mmb.createAddress("vadim.isaev@metrobank.ru"));
	message.setSubject("Test message");
	message.addPart(mmb.createTextPart("Test message"));
	sender.setAlwaysBlindCopyTo(mmb.createAddress("vadim.isaev@metrobank.ru"));
	sender.send(message);
    }

}
