package com.lapsa.mailutil.impl;

import javax.mail.Session;

import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailSender;
import com.lapsa.mailutil.MailService;

public abstract class BaseMailHelperImpl implements MailService {

    protected abstract Session getSession();

    @Override
    public MailSender createSender() throws MailException {
	return new MailSenderImpl(getSession());
    }

    @Override
    public MailMessageBuilder createBuilder() throws MailException {
	return new MailMessageBuilderImpl();
    }
}
