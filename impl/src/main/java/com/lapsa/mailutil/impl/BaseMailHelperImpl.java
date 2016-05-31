package com.lapsa.mailutil.impl;

import static com.lapsa.mailutil.impl.MailPropertyNames.*;

import javax.mail.Session;

import com.lapsa.mailutil.MailAddress;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailReceiver;
import com.lapsa.mailutil.MailSender;
import com.lapsa.mailutil.MailService;

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
