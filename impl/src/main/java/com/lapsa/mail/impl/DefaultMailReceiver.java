package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailReceiver;

import tech.lapsa.java.commons.logging.MyLogger;

final class DefaultMailReceiver implements MailReceiver {

    final transient DefaultMailService service;

    private transient final MyLogger logger = MyLogger.newBuilder() //
	    .withNameOf(MailReceiver.class) //
	    .build();

    private transient Store store = null;
    private transient Folder folder = null;

    DefaultMailReceiver(final DefaultMailService service) {
	this.service = service;
    }

    @Override
    public boolean hasMessages() throws MailException {
	try {
	    autoConnect();
	    final int count = folder.getMessageCount();
	    return count > 0;
	} catch (final MessagingException e) {
	    logger.SEVERE.log(e, "MAIL_RECEIVE_ERROR");
	    throw new MailException(e);
	}
    }

    @Override
    public void сlearMessages() throws MailException {
	try {
	    autoConnect();
	    final Message[] messages = folder.getMessages();
	    for (final Message message : messages)
		message.setFlag(Flag.DELETED, true);
	    folder.expunge();
	} catch (final MessagingException e) {
	    logger.SEVERE.log(e, "MAIL_RECEIVE_ERROR");
	    throw new MailException(e);
	}
    }

    @Override
    public int countMessages() throws MailException {
	try {
	    autoConnect();
	    return folder.getMessageCount();
	} catch (final MessagingException e) {
	    logger.SEVERE.log(e, "MAIL_RECEIVE_ERROR");
	    throw new MailException(e);
	}
    }

    private void autoConnect() throws MessagingException {
	if (store == null)
	    store = service.session.getStore();
	if (!store.isConnected()) {
	    store.connect(service.session.getProperty(MAIL_USER), service.session.getProperty(MAIL_PASSWORD));
	    logger.FINE.log("MAIL_RECEIVE store connected OK");
	}
	if (folder == null)
	    folder = store.getFolder("INBOX");
	if (!folder.isOpen()) {
	    folder.open(Folder.READ_WRITE);
	    logger.FINE.log("MAIL_RECEIVE folder open OK");
	}
    }

    @Override
    public void close() throws MailException {
	if (folder != null && folder.isOpen())
	    try {
		folder.close(false);
		logger.FINE.log("MAIL_CHECK folder closed OK");
	    } catch (final MessagingException e) {
		logger.SEVERE.log(e, "MAIL_RECEIVE_ERROR");
		throw new MailException(e);
	    }
	if (store != null && store.isConnected())
	    try {
		store.close();
		logger.FINE.log("MAIL_CHECK store disconnected OK");
	    } catch (final MessagingException e) {
		logger.SEVERE.log(e, "MAIL_RECEIVE_ERROR");
		throw new MailException(e);
	    }
    }

}
