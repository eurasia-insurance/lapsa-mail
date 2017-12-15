package test.mail;

import static test.mail.MailSessionHelper.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

public class SendTestCase {

    private static final String PATH_TO_TEST_RESOURCE = "policy_sample.jpg";
    private static final String PATH_TO_TEST_FILE = "src/test/resources/policy_sample.jpg";

    private MailServiceFactory factory;
    private MailService service;

    @Before
    public void prepareSession() throws MailException {
	factory = MailServiceFactory.getInstance();
	service = factory.createService(MailSessionHelper.PROPERTIES);
    }

    @Test(expected = InvalidMessageException.class)
    public void testEmptyRecipientsException() throws MailException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    final MailMessageBuilder mmb = service.createBuilder();
	    final MailMessage message = mmb.createMessage();
	    final MailMessagePart part = mmb.createTextPart("Test message");
	    message.addPart(part);
	    message.setSubject("testEmptyRecipientsException()");
	    sender.send(message);
	}
    }

    @Test
    public void testEmptyBody() throws MailException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    final MailMessageBuilder mmb = service.createBuilder();
	    final MailMessage message = mmb.createMessage();
	    message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testEmptyBody()");
	    sender.send(message);
	}
    }

    @Test
    public void testSend() throws MailException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    final MailMessageBuilder mmb = service.createBuilder();
	    final MailMessage message = mmb.createMessage();
	    message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSend()");
	    message.addPart(mmb.createTextPart("testSend"));
	    sender.send(message);
	}
    }

    @Test
    public void testSendAlwaysCopyTo() throws MailException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    final MailMessageBuilder mmb = service.createBuilder();
	    final MailMessage message = mmb.createMessage();
	    message.addTORecipient(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendAlwaysCopyTo()");
	    message.addPart(mmb.createTextPart("testSendAlwaysCopyTo"));
	    sender.setAlwaysBlindCopyTo(mmb.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    sender.send(message);
	}
    }

    @Test
    public void testSendImage_InputStream() throws MailException, IOException, InvalidMessageException {
	try (MailSender sender = service.createSender(); InputStream is = testFileInputStream()) {
	    final MailMessageBuilder builder = service.createBuilder();
	    final MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_InputStream()");
	    message.addPart(builder.createStreamPart("PICTURE", "image/jpeg", is));
	    sender.send(message);
	}
    }

    @Test
    public void testSendImage_File() throws MailException, IOException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    final MailMessageBuilder builder = service.createBuilder();
	    final MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_File");
	    final MailMessagePart part = builder.createFilePart(new File(PATH_TO_TEST_FILE));
	    message.addPart(part);
	    sender.send(message);
	}
    }

    @Test
    public void testSendImage_Bytes() throws MailException, IOException, InvalidMessageException {
	try (MailSender sender = service.createSender()) {
	    final MailMessageBuilder builder = service.createBuilder();
	    final MailMessage message = builder.createMessage();
	    message.addTORecipient(builder.createAddress(MAIL_TEST_RECIPIENT_ADDRESS));
	    message.setSubject("testSendImage_Bytes");
	    final byte[] bytes = testFileBytes();
	    message.addPart(builder.createByteArrayPart("PICTURE.jpg", "image/jpeg", bytes));
	    sender.send(message);
	}
    }

    private InputStream testFileInputStream() {
	return this.getClass().getClassLoader().getResourceAsStream(PATH_TO_TEST_RESOURCE);
    }

    private byte[] testFileBytes() throws IOException {
	try (InputStream is = testFileInputStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	    final byte[] buff = new byte[256];
	    int readed;
	    while ((readed = is.read(buff)) != -1)
		baos.write(buff, 0, readed);
	    return baos.toByteArray();
	}
    }

}
