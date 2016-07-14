package test.com.lapsa.mail;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.mail.Session;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailServiceFactory;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;

public class BasicTestCase {

    private static Session session;

    @BeforeClass
    public static void prepareSession() {
	session = Session.getDefaultInstance(new Properties());
    }

    @Test
    public void testCreateFactory() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getDefaultInstance();
	assertNotNull(mhf);
    }

    @Test
    public void testCreateService() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getDefaultInstance();
	MailService mh = mhf.createService(session);
	assertNotNull(mh);
    }

    @Test
    public void testCreateSender() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getDefaultInstance();
	MailService mh = mhf.createService(session);
	MailSender ms = mh.createSender();
	assertNotNull(ms);
    }

    @Test
    public void testCreateBuilder() throws MailException {
	MailServiceFactory mhf = MailServiceFactory.getDefaultInstance();
	MailService mh = mhf.createService(session);
	MailMessageBuilder mmb = mh.createBuilder();
	assertNotNull(mmb);
    }
}
