package tech.lapsa.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;
import static tech.lapsa.lapsa.mail.impl.Checks.*;

import java.nio.charset.Charset;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;

import tech.lapsa.lapsa.mail.MailBuilderException;
import tech.lapsa.lapsa.mail.MailFactoryBuilder;

public final class DefaultMailFactoryBuilder implements MailFactoryBuilder {

    public DefaultMailFactoryBuilder() {
    }

    Charset defaultCharset = Charset.forName("UTF-8");

    MailAddress defaultSender = null;

    MailAddress defaultRecipient = null;
    MailAddress alwaysBlindCopyTo = null;

    MailAddress forwardAllMailTo = null;

    String username = null;
    String password = null;

    private final Authenticator a = null;
    private Properties properties = null;

    @Override
    public DefaultMailFactory build() throws MailBuilderException {
	final Session defaultSession = a != null ? Session.getInstance(properties, a)
		: Session.getInstance(properties);
	return build(defaultSession);
    }

    DefaultMailFactory build(final Session session) throws MailBuilderException {
	return new DefaultMailFactory(this, session);
    }

    @Override
    public DefaultMailFactoryBuilder withAlwaysBlindCopyTo(final String address) throws MailBuilderException {
	alwaysBlindCopyTo = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withAlwaysBlindCopyTo(final String address, final String friendlyName)
	    throws MailBuilderException {
	alwaysBlindCopyTo = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withAuth(final String user, final String password) throws MailBuilderException {
	builderRequireNonNull(user, "User name can not be null");
	builderRequireNonNull(password, "Password can not be null");
	username = user;
	this.password = password;
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withDebug(final boolean debug) {
	new PropertiesBuilder()
		.withProperty(PROPERTY_MAIL_DEBUG, debug)
		.mergeTo(properties);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withDefaultCharset(final Charset charset) throws MailBuilderException {
	defaultCharset = builderRequireNonNull(charset, "Charset can not be null");
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withDefaultRecipient(final String address) throws MailBuilderException {
	defaultRecipient = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withDefaultRecipient(final String address, final String friendlyName)
	    throws MailBuilderException {
	defaultRecipient = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withDefaultSender(final String address) throws MailBuilderException {
	defaultSender = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withDefaultSender(final String address, final String friendlyName)
	    throws MailBuilderException {
	defaultSender = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withForwardAllMailTo(final String address) throws MailBuilderException {
	forwardAllMailTo = new MailAddress(address, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withForwardAllMailTo(final String address, final String friendlyName)
	    throws MailBuilderException {
	forwardAllMailTo = new MailAddress(address, friendlyName, defaultCharset);
	return this;
    }

    @Override
    public DefaultMailFactoryBuilder withProperties(final Properties properties) throws MailBuilderException {
	this.properties = builderRequireNonNull(properties, "Properties can not be null");

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
    private final Properties properties = new Properties();

    Properties build() {
	return (Properties) properties.clone();
    }

    void mergeTo(final Properties mergeTo) {
	for (final Object o : properties.keySet())
	    mergeTo.put(o, properties.get(o));
    }

    PropertiesBuilder withProperty(final String key, final Object value) {
	properties.put(key, value);
	return this;
    }
}
