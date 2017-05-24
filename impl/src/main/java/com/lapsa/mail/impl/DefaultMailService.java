package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;

import javax.mail.Session;

import com.lapsa.mail.MailAddress;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailReceiver;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;

class DefaultMailService implements MailService {

    final transient Session session;

    DefaultMailService(final Session session) {
	this.session = session;
    }

    @Override
    final public MailSender createSender() throws MailException {
	return new DefaultMailSender(this);
    }

    @Override
    final public MailReceiver createReceiver() throws MailException {
	return new DefaultMailReceiver(this);
    }

    @Override
    final public MailMessageBuilder createBuilder() throws MailException {
	return new DefaultMailMessageBuilder(this);
    }

    @Override
    final public MailAddress getDefaultSender() {
	final String from = session.getProperty(MAIL_FROM);
	if (from != null)
	    return new DefaultMailAddress(this, from, "");
	return null;
    }

    @Override
    final public MailAddress getDefaultRecipient() {
	final String from = session.getProperty(MAIL_TO);
	if (from != null)
	    return new DefaultMailAddress(this, from, "");
	return null;
    }

    @Override
    final public MailAddress getDefaultBCCRecipient() {
	final String from = session.getProperty(MAIL_BCC);
	if (from != null)
	    return new DefaultMailAddress(this, from, "");
	return null;
    }
}
