package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailPropertyNames.*;

import javax.mail.Session;

import com.lapsa.mail.MailAddress;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailReceiver;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;

public abstract class BaseMailService implements MailService {

    protected abstract Session getSession();

    @Override
    public MailSender createSender() throws MailException {
	return new DefaultMailSender(this, getSession());
    }

    @Override
    public MailReceiver createReceiver() throws MailException {
	return new DefaultMailReceiver(this, getSession());
    }

    @Override
    public MailMessageBuilder createBuilder() throws MailException {
	return new DefaultMailMessageBuilder(this);
    }

    @Override
    public MailAddress getDefaultSender() {
	final String from = getSession().getProperty(MAIL_FROM);
	if (from != null)
	    return new DefaultMailAddress(from, "");
	return null;
    }

    @Override
    public MailAddress getDefaultRecipient() {
	final String from = getSession().getProperty(MAIL_TO);
	if (from != null)
	    return new DefaultMailAddress(from, "");
	return null;
    }

    @Override
    public MailAddress getDefaultBCCRecipient() {
	final String from = getSession().getProperty(MAIL_BCC);
	if (from != null)
	    return new DefaultMailAddress(from, "");
	return null;
    }
}
