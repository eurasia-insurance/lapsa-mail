package test.com.lapsa.mail2;

import static test.com.lapsa.mail.MailSessionHelper.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.lapsa.mail2.MailException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.impl.SessionMailFactory;

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
		.withPartText(this.getClass().getName() + ".testSend1")
		.build()
		.send();
    }

}
