package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailPropertyNames.*;

import javax.mail.Session;

import com.lapsa.mail.MailAddress;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailReceiver;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;

public abstract class BaseMailHelperImpl implements MailService {

    protected abstract Session getSession();

    @Override
    public MailSender createSender() throws MailException {
	return new MailSenderImpl(this, getSession());
    }

    @Override
    public MailReceiver createReceiver() throws MailException {
	return new MailReceiverImpl(this, getSession());
    }

    @Override
    public MailMessageBuilder createBuilder() throws MailException {
	return new MailMessageBuilderImpl(this);
    }

    @Override
    public MailAddress getDefaultSender() {
	String from = getSession().getProperty(MAIL_FROM);
	if (from != null) {
	    return new MailAddressImpl(from, "");
	}
	return null;
    }

    @Override
    public MailAddress getDefaultRecipient() {
	String from = getSession().getProperty(MAIL_TO);
	if (from != null) {
	    return new MailAddressImpl(from, "");
	}
	return null;
    }

    @Override
    public MailAddress getDefaultBCCRecipient() {
	String from = getSession().getProperty(MAIL_BCC);
	if (from != null) {
	    return new MailAddressImpl(from, "");
	}
	return null;
    }
}
