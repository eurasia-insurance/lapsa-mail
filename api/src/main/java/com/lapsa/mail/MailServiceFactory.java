package com.lapsa.mail;

import java.util.ServiceLoader;
import java.util.function.Predicate;

import javax.mail.Session;

public interface MailServiceFactory {

    MailService createService(Session session) throws MailException;

    static MailServiceFactory getInstance() throws MailException {
	return getInstance(factory -> true);
    }

    static MailServiceFactory getInstance(final Class<MailServiceFactory> clazz) throws MailException {
	return getInstance(factory -> factory.getClass() == clazz);
    }

    @SuppressWarnings("unchecked")
    static MailServiceFactory getInstance(final String implementationClass)
	    throws MailException, ClassNotFoundException {
	return getInstance((Class<MailServiceFactory>) Class.forName(implementationClass));
    }

    static MailServiceFactory getInstance(final Predicate<MailServiceFactory> func) throws MailException {
	final ServiceLoader<MailServiceFactory> mailFactorySPI = ServiceLoader.load(MailServiceFactory.class);
	for (final MailServiceFactory factory : mailFactorySPI)
	    if (func.test(factory))
		return factory;
	throw new MailException("There is no any registered MailServiceFactory service provider");
    }

}
