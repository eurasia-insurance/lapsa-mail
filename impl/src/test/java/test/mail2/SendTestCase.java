package test.mail2;

import static test.mail.MailSessionHelper.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import tech.lapsa.javx.mail.MailException;
import tech.lapsa.javx.mail.MailFactory;
import tech.lapsa.javx.mail.impl.SessionMailFactory;

public class SendTestCase {

    private static final String PATH_TO_TEST_RESOURCE = "policy_sample.jpg";
    private static final String PATH_TO_TEST_FILE = "src/test/resources/policy_sample.jpg";

    private MailFactory factory;

    @Before
    public void prepareSession() throws MailException {
	factory = new SessionMailFactory(MailSessionHelper.SESSION);
    }

    @Test
    public void testSend1() throws MailException, IOException {
	factory.newMailBuilder()
		.withTORecipient(MAIL_TEST_RECIPIENT_ADDRESS)
		.withSubject(this.getClass().getName() + ".testSend1")
		.withTextPart(this.getClass().getName() + ".testSend1")
		.withBytesAttached(testFileBytes(), "image/jpeg", "PICTURE.jpg")
		.build()
		.send();
    }

    @Test
    public void testSend2() throws MailException, IOException {
	factory.newMailBuilder()
		.withTORecipient(MAIL_TEST_RECIPIENT_ADDRESS)
		.withSubject(this.getClass().getName() + ".testSend2")
		.withTextPart(this.getClass().getName() + ".testSend2")
		.withStreamAttached(testFileInputStream(), "image/jpeg", "PICTURE.jpg")
		.build()
		.send();
    }

    @Test
    public void testSend3() throws MailException, IOException {
	factory.newMailBuilder()
		.withTORecipient(MAIL_TEST_RECIPIENT_ADDRESS)
		.withSubject(this.getClass().getName() + ".testSend3")
		.withTextPart(this.getClass().getName() + ".testSend3")
		.withFileAttached(new File(PATH_TO_TEST_FILE))
		.build()
		.send();
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
