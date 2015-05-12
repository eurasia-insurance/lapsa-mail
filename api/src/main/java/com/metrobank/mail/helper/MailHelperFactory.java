package com.metrobank.mail.helper;

import java.util.*;

import javax.mail.*;

public abstract class MailHelperFactory {

	public static final String DEFAULT_IMPL_NAME = "DEFAULT";

	@Deprecated
	public synchronized static final MailHelperFactory getInstance() {
		try {
			return getMailHelperFactory(DEFAULT_IMPL_NAME);
		} catch (MailHelperException e) {
			throw new RuntimeException(e);
		}
	}

	public static final MailHelperFactory getDefaultMailHelperFactory()
			throws MailHelperException {
		return getMailHelperFactory(DEFAULT_IMPL_NAME);
	}

	public static final MailHelperFactory getMailHelperFactory(String name)
			throws MailHelperException {
		ServiceLoader<MailHelperFactory> mailHelperFactorySPI = ServiceLoader
				.load(MailHelperFactory.class);
		for (MailHelperFactory factory : mailHelperFactorySPI) {
			if (factory.getName().equals(name)) {
				return factory;
			}
		}
		throw new MailHelperException(
				"There is no any registered MailHelperFactory service provider");
	}

	public abstract String getName();

	public abstract MailHelper getHelper(Session session)
			throws MailHelperException;
}
