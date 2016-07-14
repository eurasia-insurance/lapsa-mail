package com.lapsa.mail.impl;

import javax.mail.Session;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailServiceFactory;
import com.lapsa.mail.MailService;

public class DefaultMailServiceFactory extends MailServiceFactory {

    @Override
    public String getName() {
	return MailServiceFactory.DEFAULT_IMPL_NAME;
    }

    @Override
    public MailService createService(final Session session) throws MailException {
	return new DefaultMailSerivce(session);
    }

}
