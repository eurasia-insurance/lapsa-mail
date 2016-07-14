package com.lapsa.mail;

import java.util.ServiceLoader;

import javax.mail.Session;

public abstract class MailServiceFactory {

    public static final String DEFAULT_IMPL_NAME = "DEFAULT";

    public static final MailServiceFactory getDefaultInstance() throws MailException {
	return getInstanceByName(DEFAULT_IMPL_NAME);
    }

    public static final MailServiceFactory getInstanceByName(final String name) throws MailException {
	final ServiceLoader<MailServiceFactory> mailFactorySPI = ServiceLoader.load(MailServiceFactory.class);
	for (final MailServiceFactory factory : mailFactorySPI)
	    if (factory.getName().equals(name))
		return factory;
	throw new MailException("There is no any registered MailServiceFactory service provider");
    }

    public abstract String getName();

    public abstract MailService createService(Session session) throws MailException;
}
