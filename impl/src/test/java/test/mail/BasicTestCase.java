package test.mail;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;
import com.lapsa.mail.MailServiceFactory;

public class BasicTestCase {

    private MailServiceFactory factory;
    private MailService service;

    @Before
    public void prepareSession() throws MailException {
	factory = MailServiceFactory.getInstance();
	if (factory != null)
	    service = factory.createService(MailSessionHelper.PROPERTIES);
    }

    @Test
    public void testCreateFactory() throws MailException {
	assertNotNull(factory);
    }

    @Test
    public void testCreateService() throws MailException {
	assertNotNull(service);
    }

    @Test
    public void testCreateSender() throws MailException {
	MailSender ms = service.createSender();
	assertNotNull(ms);
    }

    @Test
    public void testCreateBuilder() throws MailException {
	MailMessageBuilder mmb = service.createBuilder();
	assertNotNull(mmb);
    }
}
