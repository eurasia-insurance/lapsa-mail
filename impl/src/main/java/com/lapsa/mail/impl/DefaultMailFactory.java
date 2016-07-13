package com.lapsa.mailutil.impl;

import javax.mail.Session;

import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailFactory;
import com.lapsa.mailutil.MailService;

public class DefaultMailFactory extends MailFactory {

    @Override
    public String getName() {
	return MailFactory.DEFAULT_IMPL_NAME;
    }

    @Override
    public MailService getService(Session session) throws MailException {
	return new MailSerivceFactoryImpl(session);
    }

}
