package com.lapsa.mailutil.test;

import static com.lapsa.mailutil.test.MailSessionFactory.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

public class SendTestCase {

    private static final String PATH_TO_TEST_RESOURCE = "policy_sample.jpg";
    private static final String PATH_TO_TEST_FILE = "src/test/resources/policy_sample.jpg";

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	session = MailSessionFactory.createSession();
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyRecipientsException() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	try (MailSender sender = mh.createSender()) {
	    MailMessageBuilder mmb = mh.createBuilder();
	    MailMessage message = mmb.createMessage();
	    MailMessagePart part = mmb.createTextPart("Test message");
	    message.addPart(part);
	    message.setSubject("testEmptyRecipientsException()");
	    sender.send(message);
	}
    }

    @Test
    public void testEmptyBody() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	try (MailSender sender = mh.createSender()) {
	    MailMessageBuilder mmb = mh.createBuilder();
	    MailMessage message = mmb.createMessage();
	    message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testEmptyBody()");
	    sender.send(message);
	}
    }

    @Test
    public void testSend() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	try (MailSender sender = mh.createSender()) {
	    MailMessageBuilder mmb = mh.createBuilder();
	    MailMessage message = mmb.createMessage();
	    message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSend()");
	    message.addPart(mmb.createTextPart("testSend"));
	    sender.send(message);
	}
    }

    @Test
    public void testSendAlwaysCopyTo() throws MailException, InvalidMessageException {
	MailFactory mhf = MailFactory.getDefaultMailFactory();
	MailService mh = mhf.getService(session);
	try (MailSender sender = mh.createSender()) {
	    MailMessageBuilder mmb = mh.createBuilder();
	    MailMessage message = mmb.createMessage();
	    message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendAlwaysCopyTo()");
	    message.addPart(mmb.createTextPart("testSendAlwaysCopyTo"));
	    sender.setAlwaysBlindCopyTo(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    sender.send(message);
	}
    }

    @Test
    public void testSendImage_InputStream() throws MailException, IOException, InvalidMessageException {
	MailFactory mf = MailFactory.getDefaultMailFactory();
	MailService ms = mf.getService(session);
	try (MailSender sender = ms.createSender(); InputStream is = testFileInputStream()) {
	    MailMessageBuilder builder = ms.createBuilder();
	    MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_InputStream()");
	    message.addPart(builder.createStreamPart("PICTURE", "image/jpeg", is));
	    sender.send(message);
	}
    }

    @Test
    public void testSendImage_File() throws MailException, IOException, InvalidMessageException {
	MailFactory mf = MailFactory.getDefaultMailFactory();
	MailService ms = mf.getService(session);
	try (MailSender sender = ms.createSender()) {
	    MailMessageBuilder builder = ms.createBuilder();
	    MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_File");
	    MailMessagePart part = builder.createFilePart(new File(PATH_TO_TEST_FILE));
	    message.addPart(part);
	    sender.send(message);
	}
    }

    @Test
    public void testSendImage_Bytes() throws MailException, IOException, InvalidMessageException {
	MailFactory mf = MailFactory.getDefaultMailFactory();
	MailService ms = mf.getService(session);
	try (MailSender sender = ms.createSender()) {
	    MailMessageBuilder builder = ms.createBuilder();
	    MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_Bytes");
	    byte[] bytes = testFileBytes();
	    message.addPart(builder.createByteArrayPart("PICTURE.jpg", "image/jpeg", bytes));
	    sender.send(message);
	}
    }

    private InputStream testFileInputStream() {
	return this.getClass().getClassLoader().getResourceAsStream(PATH_TO_TEST_RESOURCE);
    }

    private byte[] testFileBytes() throws IOException {
	try (InputStream is = testFileInputStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	    byte[] buff = new byte[256];
	    int readed;
	    while ((readed = is.read(buff)) != -1)
		baos.write(buff, 0, readed);
	    return baos.toByteArray();
	}
    }

}
