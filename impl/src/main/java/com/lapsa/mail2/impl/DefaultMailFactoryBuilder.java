package com.lapsa.mail2.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;
import static com.lapsa.mail2.impl.Checks.*;

import java.nio.charset.Charset;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.MailFactoryBuilder;

public final class DefaultMailFactoryBuilder implements MailFactoryBuilder {

    Authenticator a = null;

    Properties properties = null;
    Charset defaultCharset = Charset.forName("UTF-8");

    MailAddress defaultSender = null;

    MailAddress defaultRecipient = null;
    MailAddress alwaysBlindCopyTo = null;

    MailAddress forwardAllMailTo = null;
    @Override
    public MailFactory build() {
	Session session = Session.getDefaultInstance(properties, a);
	return new DefaultMailFactory(session, defaultCharset, alwaysBlindCopyTo, forwardAllMailTo, defaultRecipient,
		defaultSender);
    }

    @Override
    public MailFactoryBuilder withAlwaysBlindCopyTo(String address) throws MailBuilderException {
	this.alwaysBlindCopyTo = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withAlwaysBlindCopyTo(String address, String friendlyName) throws MailBuilderException {
	this.alwaysBlindCopyTo = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withAuth(String user, String password) throws MailBuilderException {
	builderRequireNonNull(user, "User name can not be null");
	builderRequireNonNull(password, "Password can not be null");
	this.a = new Authenticator() {
	    public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	    }
	};
	return this;
    }

    @Override
    public MailFactoryBuilder withDebug(boolean debug) {
	new PropertiesBuilder()
		.withProperty(PROPERTY_MAIL_DEBUG, debug)
		.mergeTo(properties);
	return this;
    }

    @Override
    public MailFactoryBuilder withDefaultCharset(Charset charset) throws MailBuilderException {
	this.defaultCharset = builderRequireNonNull(charset, "Charset can not be null");
	return this;
    }

    @Override
    public MailFactoryBuilder withDefaultRecipient(String address) throws MailBuilderException {
	this.defaultRecipient = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withDefaultRecipient(String address, String friendlyName) throws MailBuilderException {
	this.defaultRecipient = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withDefaultSender(String address) throws MailBuilderException {
	this.defaultSender = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withDefaultSender(String address, String friendlyName) throws MailBuilderException {
	this.defaultSender = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withForwardAllMailTo(String address) throws MailBuilderException {
	this.forwardAllMailTo = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withForwardAllMailTo(String address, String friendlyName) throws MailBuilderException {
	this.forwardAllMailTo = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public MailFactoryBuilder withProperties(Properties properties) throws MailBuilderException {
	this.properties = builderRequireNonNull(properties, "Properties can not be null");

	if (properties.containsKey(MAIL_AUTHENTIFICATOR_OBJECT)) {
	    final Object authentificatorObject = properties.get(MAIL_AUTHENTIFICATOR_OBJECT);
	    try {
		final Authenticator a = (Authenticator) authentificatorObject;
		this.a = a;
	    } catch (Exception e) {
		throw builderWrapException(e,
			String.format("Invalid object of type %1$s", authentificatorObject.getClass()));
	    }
	}

	if (properties.containsKey(MAIL_USER)
		&& properties.containsKey(MAIL_PASSWORD)) {
	    final String user = properties.getProperty(MAIL_USER);
	    final String password = properties.getProperty(MAIL_PASSWORD);
	    withAuth(user, password);
	}

	if (properties.containsKey(MAIL_BCC))
	    withAlwaysBlindCopyTo(properties.getProperty(MAIL_BCC));

	if (properties.containsKey(MAIL_FORCETO))
	    withForwardAllMailTo(properties.getProperty(MAIL_FORCETO));

	if (properties.containsKey(MAIL_FROM))
	    withDefaultSender(properties.getProperty(MAIL_FROM));

	if (properties.containsKey(MAIL_TO))
	    withDefaultRecipient(properties.getProperty(MAIL_TO));

	return this;
    }

    static final String PROPERTY_MAIL_DEBUG = "mail.debug";

}

class PropertiesBuilder {
    private Properties properties = new Properties();

    Properties build() {
	return (Properties) properties.clone();
    }

    void mergeTo(Properties mergeTo) {
	for (Object o : properties.keySet())
	    mergeTo.put(o, properties.get(o));
    }

    PropertiesBuilder withProperty(String key, Object value) {
	properties.put(key, value);
	return this;
    }
}
