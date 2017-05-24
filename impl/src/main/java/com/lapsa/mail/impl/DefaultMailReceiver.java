package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailReceiver;

final class DefaultMailReceiver implements MailReceiver {

    final transient DefaultMailService service;

    private transient final Logger logger;
    private transient Store store = null;
    private transient Folder folder = null;

    DefaultMailReceiver(final DefaultMailService service) {
	this.service = service;
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
	    store = service.session.getStore();
	if (!store.isConnected()) {
	    store.connect(service.session.getProperty(MAIL_USER), service.session.getProperty(MAIL_PASSWORD));
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
