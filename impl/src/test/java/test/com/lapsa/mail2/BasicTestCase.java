package test.com.lapsa.mail2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lapsa.mail2.MailMessageBuilder;
import com.lapsa.mail2.MailException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.MailFactoryBuilder;

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
	MailMessageBuilder mmb = factory.newMailBuilder();
	assertNotNull(mmb);
    }
}
