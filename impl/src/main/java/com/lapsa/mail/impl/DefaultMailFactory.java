package com.lapsa.mail.impl;

import javax.mail.Session;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailFactory;
import com.lapsa.mail.MailService;

public class DefaultMailFactory extends MailFactory {

    @Override
    public String getName() {
	return MailFactory.DEFAULT_IMPL_NAME;
    }

    @Override
    public MailService getService(final Session session) throws MailException {
	return new MailSerivceFactoryImpl(session);
    }

}
