package com.lapsa.mailhelper.impl;

import javax.mail.Session;

import com.lapsa.mailhelper.MailService;
import com.lapsa.mailhelper.MailHelperException;
import com.lapsa.mailhelper.MailHelperFactory;

public class MailHelperFactoryImpl extends MailHelperFactory {

	@Override
	public String getName() {
		return MailHelperFactory.DEFAULT_IMPL_NAME;
	}

	@Override
	public MailService getHelper(Session session) throws MailHelperException {
		return new MailSerivceFactoryImpl(session);
	}

}
