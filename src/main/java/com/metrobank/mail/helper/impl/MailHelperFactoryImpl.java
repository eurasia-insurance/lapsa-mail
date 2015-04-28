package com.metrobank.mail.helper.impl;

import javax.mail.Session;

import com.metrobank.mail.helper.MailHelper;
import com.metrobank.mail.helper.MailHelperException;
import com.metrobank.mail.helper.MailHelperFactory;

public class MailHelperFactoryImpl extends MailHelperFactory {

    @Override
    public String getName() {
	return MailHelperFactory.DEFAULT_IMPL_NAME;
    }

    @Override
    public MailHelper getHelper(Session session) throws MailHelperException {
	return new MailHelperImpl(session);
    }

}
