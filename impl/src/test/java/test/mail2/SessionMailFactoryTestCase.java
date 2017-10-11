package test.mail2;

import static test.mail.MailSessionHelper.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import tech.lapsa.javx.mail.MailException;
import tech.lapsa.javx.mail.MailFactory;
import tech.lapsa.javx.mail.impl.SessionMailFactory;

public class SessionMailFactoryTestCase {

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
		.build()
		.send();
    }

}
