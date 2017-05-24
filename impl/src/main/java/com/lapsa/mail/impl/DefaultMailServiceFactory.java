package com.lapsa.mail.impl;

import java.util.Properties;

import javax.mail.Session;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailService;
import com.lapsa.mail.MailServiceFactory;

public final class DefaultMailServiceFactory implements MailServiceFactory {

    @Override
    public MailService createService() throws MailException {
	return createService(new Properties());
    }

    @Override
    public MailService createService(Properties props) throws MailException {
	return new DefaultMailSerivce(Session.getDefaultInstance(props));
    }

}
