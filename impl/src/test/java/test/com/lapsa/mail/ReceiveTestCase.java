package test.com.lapsa.mail;

import static org.junit.Assert.*;

import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lapsa.mail.InvalidMessageException;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailServiceFactory;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailReceiver;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;

public class ReceiveTestCase {

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	session = MailSessionHelper.createDefaultSession();
    }

    @Test
    public void testHasMessages() throws MailException, InvalidMessageException, InterruptedException {
	MailServiceFactory mhf = MailServiceFactory.getDefaultInstance();
	MailService mh = mhf.createService(session);
	sendOneMessageForTestPurposes(mh);
	try (MailReceiver receiver = mh.createReceiver()) {
	    boolean hasMessages = receiver.hasMessages();
	    assertTrue(hasMessages);
	}
    }

    @Test
    public void testClearMessages() throws MailException, InterruptedException, InvalidMessageException {
	MailServiceFactory mhf = MailServiceFactory.getDefaultInstance();
	MailService mh = mhf.createService(session);
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
	    sender.send(b.createMessage(b.createAddress(MailSessionHelper.MAIL_TEST_TO_ADDRESS),
		    "Test for receive and clean"));
	    Thread.sleep(2000);
	}
    }
}