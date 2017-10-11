package test.mail;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lapsa.mail.InvalidMessageException;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailReceiver;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;
import com.lapsa.mail.MailServiceFactory;

public class ReceiveTestCase {

    private MailServiceFactory factory;
    private MailService service;

    @Before
    public void prepareSession() throws MailException {
	factory = MailServiceFactory.getInstance();
	service = factory.createService(MailSessionHelper.PROPERTIES);
    }

    @Test
    public void testHasMessages() throws MailException, InvalidMessageException, InterruptedException {
	sendOneMessageForTestPurposes();
	try (MailReceiver receiver = service.createReceiver()) {
	    boolean hasMessages = receiver.hasMessages();
	    assertTrue(hasMessages);
	}
    }

    @Test
    public void testClearMessages() throws MailException, InterruptedException, InvalidMessageException {
	sendOneMessageForTestPurposes();
	try (MailReceiver receiver = service.createReceiver()) {
	    boolean hasMessages = receiver.hasMessages();
	    if (hasMessages) {
		receiver.сlearMessages();
		boolean testHasMessages = receiver.hasMessages();
		assertFalse("clearMessages() failed. The folder is not  empty.", testHasMessages);
	    } else
		fail("The folder is empty. Nothing to test");
	}
    }

    private void sendOneMessageForTestPurposes()
	    throws MailException, InterruptedException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    MailMessageBuilder b = service.createBuilder();
	    sender.send(b.createMessage(b.createAddress(MailSessionHelper.MAIL_TEST_TO_ADDRESS),
		    "Test for receive and clean"));
	    Thread.sleep(2000);
	}
    }
}