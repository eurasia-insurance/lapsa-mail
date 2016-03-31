package com.lapsa.mailutil.impl;

import static com.lapsa.mailutil.impl.MailPropertyNames.*;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessage;
import com.lapsa.mailutil.MailReceiver;

class MailReceiverImpl implements MailReceiver {

    private final Session session;
    private final Logger logger;

    MailReceiverImpl(Session session) {
	this.session = session;
	this.logger = Logger.getLogger(this.getClass().getCanonicalName());
    }

    @Override
    public boolean hasNewMessages() throws MailException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Collection<MailMessage> getAndClear() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int countNewMessages() throws MailException {
	Store store = null;
	Folder folder = null;
	try {
	    store = session.getStore();
	    store.connect(session.getProperty(MAIL_USER), session.getProperty(MAIL_PASSWORD));
	    folder = store.getDefaultFolder();
	    folder.open(Folder.READ_ONLY);
	    return folder.getNewMessageCount();
	} catch (MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_CHECK_ERROR", e);
	    throw new MailException(e);
	} finally {
	    try {
		if (folder != null && folder.isOpen())
		    folder.close(false);
	    } catch (MessagingException e) {
	    }
	    try {
		if (store != null && store.isConnected())
		    store.close();
	    } catch (MessagingException e) {
	    }

	}
    }

}
