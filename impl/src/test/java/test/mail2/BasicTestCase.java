package test.mail2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tech.lapsa.javax.mail.MailException;
import tech.lapsa.javax.mail.MailFactory;
import tech.lapsa.javax.mail.MailFactoryBuilder;
import tech.lapsa.javax.mail.MailMessageBuilder;

public class BasicTestCase {

    private MailFactoryBuilder factoryBuilder;
    private MailFactory factory;

    @Before
    public void prepareSession() throws MailException {
	factoryBuilder = MailFactoryBuilder
		.newBuilder()
		.withProperties(MailSessionHelper.PROPERTIES);
	factory = factoryBuilder.build();
    }

    @Test
    public void testCreateFactory() throws MailException {
	assertNotNull(factoryBuilder);
    }

    @Test
    public void testCreateService() throws MailException {
	assertNotNull(factory);
    }

    @Test
    public void testCreateBuilder() throws MailException {
	final MailMessageBuilder mmb = factory.newMailBuilder();
	assertNotNull(mmb);
    }
}
