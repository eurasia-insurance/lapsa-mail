package com.lapsa.mailutil.test;

import static com.lapsa.mailutil.test.MailSessionFactory.*;

import java.io.IOException;
import java.io.InputStream;

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

public class SendVariantTestCase {

    private static final String IMAGE_FILE_NAME = "policy_sample.jpg";
    private static final String IMAGE_CONTENT_TYPE = "image/jpeg";
    private static final String IMAGE_RESOURCE_PATH = "/" + IMAGE_FILE_NAME;

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	session = MailSessionFactory.createSession();
    }

    @Test
    public void testSendHtmlMessageWithImage() throws MailException, IOException, InvalidMessageException {
	MailFactory mf = MailFactory.getDefaultMailFactory();
	MailService ms = mf.getService(session);

	// builder & message
	MailMessageBuilder builder = ms.createBuilder();

	MailMessage message = builder.createMessage();

	// subject
	{
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendHtmlMessageWithImage");
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
			    + "<h1>This is an image '%1$s'</h1>"
			    + "<p align=\"center\"><img src=\"cid:%1$s\" border=\"2\" width=\"100\" height=\"100\"/></p>"
			    + "</body>"
			    + "</html>",
		    IMAGE_FILE_NAME);

	    MailMessagePart part = builder.createHTMLPart(html);
	    message.addPart(part);
	}

	// send
	try (MailSender sender = ms.createSender()) {
	    sender.send(message);
	}
    }
}
