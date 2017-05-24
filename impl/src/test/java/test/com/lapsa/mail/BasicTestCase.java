package test.com.lapsa.mail;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;
import com.lapsa.mail.MailServiceFactory;

public class BasicTestCase {

    @Test
    public void testCreateFactory() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getInstance();
	assertNotNull(mhf);
    }

    @Test
    public void testCreateService() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getInstance();
	MailService mh = mhf.createService();
	assertNotNull(mh);
    }

    @Test
    public void testCreateSender() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getInstance();
	MailService mh = mhf.createService();
	MailSender ms = mh.createSender();
	assertNotNull(ms);
    }

    @Test
    public void testCreateBuilder() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getInstance();
	MailService mh = mhf.createService();
	MailMessageBuilder mmb = mh.createBuilder();
	assertNotNull(mmb);
    }
}
