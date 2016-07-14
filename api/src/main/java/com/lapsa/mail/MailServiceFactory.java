package com.lapsa.mail;

import java.util.ServiceLoader;

import javax.mail.Session;

public abstract class MailFactory {

    public static final String DEFAULT_IMPL_NAME = "DEFAULT";

    public static final MailFactory getDefaultMailFactory() throws MailException {
	return getMailFactory(DEFAULT_IMPL_NAME);
    }

    public static final MailFactory getMailFactory(final String name) throws MailException {
	final ServiceLoader<MailFactory> mailFactorySPI = ServiceLoader.load(MailFactory.class);
	for (final MailFactory factory : mailFactorySPI)
	    if (factory.getName().equals(name))
		return factory;
	throw new MailException("There is no any registered MailFactory service provider");
    }

    public abstract String getName();

    public abstract MailService getService(Session session) throws MailException;
}
