package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailReceiver;
import com.lapsa.mail.MailService;

class DefaultMailReceiver implements MailReceiver {

    private final Session session;
    private final Logger logger;

    private Store store = null;
    private Folder folder = null;

    DefaultMailReceiver(final MailService mailService, final Session session) {
	this.session = session;
	logger = Logger.getLogger(this.getClass().getCanonicalName());
    }

    @Override
    public boolean hasMessages() throws MailException {
	try {
	    autoConnect();
	    final int count = folder.getMessageCount();
	    return count > 0;
	} catch (final MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_RECEIVE_ERROR", e);
	    throw new MailException(e);
	}
    }

    @Override
    public void сlearMessages() throws MailException {
	try {
	    autoConnect();
	    final Message[] messages = folder.getMessages();
	    for (final Message message : messages) {
		message.setFlag(Flag.DELETED, true);
	    }
	    folder.expunge();
	} catch (final MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_RECEIVE_ERROR", e);
	    throw new MailException(e);
	}
    }

    @Override
    public int countMessages() throws MailException {
	try {
	    autoConnect();
	    return folder.getMessageCount();
	} catch (final MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_RECEIVE_ERROR", e);
	    throw new MailException(e);
	}
    }

    private void autoConnect() throws MessagingException {
	if (store == null)
	    store = session.getStore();
	if (!store.isConnected()) {
	    store.connect(session.getProperty(MAIL_USER), session.getProperty(MAIL_PASSWORD));
	    logger.log(Level.FINE, "MAIL_RECEIVE store connected OK");
	}
	if (folder == null)
	    folder = store.getFolder("INBOX");
	if (!folder.isOpen()) {
	    folder.open(Folder.READ_WRITE);
	    logger.log(Level.FINE, "MAIL_RECEIVE folder open OK");
	}
    }

    @Override
    public void close() throws MailException {
	if (folder != null && folder.isOpen())
	    try {
		folder.close(false);
		logger.log(Level.FINE, "MAIL_CHECK folder closed OK");
	    } catch (final MessagingException e) {
		logger.log(Level.SEVERE, "MAIL_RECEIVE_ERROR", e);
		throw new MailException(e);
	    }
	if (store != null && store.isConnected())
	    try {
		store.close();
		logger.log(Level.FINE, "MAIL_CHECK store disconnected OK");
	    } catch (final MessagingException e) {
		logger.log(Level.SEVERE, "MAIL_RECEIVE_ERROR", e);
		throw new MailException(e);
	    }
    }

}
