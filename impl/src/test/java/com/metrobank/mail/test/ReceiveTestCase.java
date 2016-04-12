package com.metrobank.mail.test;

import static org.junit.Assert.*;

import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lapsa.mailutil.InvalidMessageException;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailFactory;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailReceiver;
import com.lapsa.mailutil.MailSender;
import com.lapsa.mailutil.MailService;

public class ReceiveTestCase {

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	session = MailSessionFactory.createSession();
    }

    @Test
    public void testHasMessages() throws MailException, InvalidMessageException, InterruptedException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	sendOneMessageForTestPurposes(mh);
	try (MailReceiver receiver = mh.createReceiver()) {
	    boolean hasMessages = receiver.hasMessages();
	    assertTrue(hasMessages);
	}
    }

    @Test
    public void testClearMessages() throws MailException, InterruptedException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	sendOneMessageForTestPurposes(mh);
	try (MailReceiver receiver = mh.createReceiver()) {
	    boolean hasMessages = receiver.hasMessages();
	    if (hasMessages) {
		receiver.сlearMessages();
		boolean testHasMessages = receiver.hasMessages();
		assertFalse("clearMessages() failed. The folder is not  empty.", testHasMessages);
	    } else
		fail("The folder is empty. Nothing to test");
	}
    }

    private void sendOneMessageForTestPurposes(MailService mh)
	    throws MailException, InterruptedException, InvalidMessageException {
	try (MailSender sender = mh.createSender()) {
	    MailMessageBuilder b = mh.createBuilder();
	    sender.send(b.createMessage(b.createAddress(MailSessionFactory.MAIL_TEST_TO_ADDRESS),
		    "Test for receive and clean"));
	    Thread.sleep(2000);
	}
    }
}