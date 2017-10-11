package test.mail;

import static test.mail.MailSessionHelper.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.lapsa.mail.InvalidMessageException;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessage;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailMessagePart;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;
import com.lapsa.mail.MailServiceFactory;

public class SendVariantTestCase {

    private static final String IMAGE_FILE_NAME = "policy_sample.jpg";
    private static final String IMAGE_CONTENT_TYPE = "image/jpeg";
    private static final String IMAGE_RESOURCE_PATH = "/" + IMAGE_FILE_NAME;

    private MailServiceFactory factory;
    private MailService service;

    @Before
    public void prepareSession() throws MailException {
	factory = MailServiceFactory.getInstance();
	service = factory.createService(MailSessionHelper.PROPERTIES);
    }

    @Test
    public void testSendHtmlMessageWithImage_Inline() throws MailException, IOException, InvalidMessageException {

	// builder & message
	MailMessageBuilder builder = service.createBuilder();

	MailMessage message = builder.createMessage();

	// subject
	{
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendHtmlMessageWithImage_Inline");
	}

	// image part
	try (InputStream is = SendVariantTestCase.class.getResourceAsStream(IMAGE_RESOURCE_PATH)) {
	    MailMessagePart part = builder.createInlineImagePart(IMAGE_CONTENT_TYPE, is, IMAGE_FILE_NAME);
	    message.addPart(part);
	}

	// html part
	{
	    String html = String.format(
		    "<!doctype html>"
			    + "<html>"
			    + "<body>"
			    + "<h1>This is an image '%1$s' as inline</h1>"
			    + "<p align=\"center\"><img src=\"cid:%1$s\" border=\"2\" width=\"100\" height=\"100\"/></p>"
			    + "</body>"
			    + "</html>",
		    IMAGE_FILE_NAME);

	    MailMessagePart part = builder.createHTMLPart(html);
	    message.addPart(part);
	}

	// send
	try (MailSender sender = service.createSender()) {
	    sender.send(message);
	}
    }

    @Test
    public void testSendHtmlMessageWithImage_Attachement() throws MailException, IOException, InvalidMessageException {

	// builder & message
	MailMessageBuilder builder = service.createBuilder();

	MailMessage message = builder.createMessage();

	// subject
	{
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendHtmlMessageWithImage_Attachement");
	}

	// image part
	try (InputStream is = SendVariantTestCase.class.getResourceAsStream(IMAGE_RESOURCE_PATH)) {
	    MailMessagePart part = builder.createStreamAttachement(is, IMAGE_CONTENT_TYPE, IMAGE_FILE_NAME);
	    message.addPart(part);
	}

	// html part
	{
	    String html = String.format(
		    "<!doctype html>"
			    + "<html>"
			    + "<body>"
			    + "<h1>This is an image '%1$s' as attachement</h1>"
			    + "</body>"
			    + "</html>",
		    IMAGE_FILE_NAME);

	    MailMessagePart part = builder.createHTMLPart(html);
	    message.addPart(part);
	}

	// send
	try (MailSender sender = service.createSender()) {
	    sender.send(message);
	}
    }

    @Test
    public void testSendException() throws MailException, IOException, InvalidMessageException {

	// builder & message
	MailMessageBuilder builder = service.createBuilder();

	MailMessage message = builder.createMessage();

	// subject
	{
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendException");
	}

	// exception part
	{
	    Exception e = new Exception("Test exception");
	    MailMessagePart part = builder.createTextPart(e);
	    message.addPart(part);
	}

	// send
	try (MailSender sender = service.createSender()) {
	    sender.send(message);
	}
    }
}
