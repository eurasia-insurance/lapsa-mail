package com.lapsa.mail2;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.function.Predicate;

public interface MailFactoryBuilder {

    MailFactory build() throws MailException;

    MailFactoryBuilder withProperties(Properties props) throws MailException;

    MailFactoryBuilder withAuth(String user, String password) throws MailException;

    MailFactoryBuilder withDefaultCharset(Charset charset) throws MailBuilderException;

    MailFactoryBuilder withAlwaysBlindCopyTo(String address) throws MailBuilderException;

    MailFactoryBuilder withAlwaysBlindCopyTo(String address, String friendlyName) throws MailBuilderException;

    MailFactoryBuilder withForwardAllMailTo(String address) throws MailBuilderException;

    MailFactoryBuilder withForwardAllMailTo(String address, String friendlyName) throws MailBuilderException;

    MailFactoryBuilder withDefaultSender(String address) throws MailBuilderException;

    MailFactoryBuilder withDefaultSender(String address, String friendlyName) throws MailBuilderException;

    MailFactoryBuilder withDefaultRecipient(String address) throws MailBuilderException;

    MailFactoryBuilder withDefaultRecipient(String address, String friendlyName) throws MailBuilderException;

    // STATIC

    static MailFactoryBuilder newBuilder() throws MailException {
	return newBuilder(factory -> true);
    }

    static <T extends MailFactoryBuilder> MailFactoryBuilder newMailFactoryBuilder(final Class<T> clazz) throws MailException {
	return newBuilder(factory -> factory.getClass() == clazz);
    }

    @SuppressWarnings("unchecked")
    static <T extends MailFactoryBuilder> MailFactoryBuilder newBuilder(final String implementationClass)
	    throws MailException, ClassNotFoundException {
	return newMailFactoryBuilder((Class<T>) Class.forName(implementationClass));
    }

    static MailFactoryBuilder newBuilder(final Predicate<MailFactoryBuilder> func) throws MailException {
	final ServiceLoader<MailFactoryBuilder> mailFactorySPI = ServiceLoader.load(MailFactoryBuilder.class);
	for (final MailFactoryBuilder factory : mailFactorySPI)
	    if (func.test(factory))
		return factory;
	throw new MailException("There is no any registered MailFactoryBuilder service provider");
    }

    MailFactoryBuilder withDebug(boolean debug);
}
