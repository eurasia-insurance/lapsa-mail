package tech.lapsa.lapsa.mail.impl;

import static tech.lapsa.lapsa.mail.impl.Checks.*;

import java.nio.charset.Charset;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.lapsa.mail.MailBuilderException;
import tech.lapsa.lapsa.mail.MailFactory;
import tech.lapsa.lapsa.mail.MailSendException;

final class DefaultMailFactory implements MailFactory {

    final Session session;
    final Transport transport;

    final MailAddress alwaysBlindCopyTo;
    final MailAddress forwardAllMailTo;

    final Charset defaultCharset;
    final MailAddress defaultRecipient;

    final MailAddress defaultSender;

    final String username;
    final String password;

    DefaultMailFactory(final DefaultMailFactoryBuilder factoryBuilder,
	    final Session session)
	    throws MailBuilderException {
	this.session = MyObjects.requireNonNull(session, "Session can not be null");
	defaultCharset = MyObjects.requireNonNull(factoryBuilder.defaultCharset, "Charset can not be null");
	alwaysBlindCopyTo = factoryBuilder.alwaysBlindCopyTo;
	forwardAllMailTo = factoryBuilder.forwardAllMailTo;
	defaultRecipient = factoryBuilder.defaultRecipient;
	defaultSender = factoryBuilder.defaultSender;

	username = factoryBuilder.username;
	password = factoryBuilder.password;

	try {
	    transport = session.getTransport();
	} catch (final NoSuchProviderException e) {
	    throw builderWrapException(e);
	}
    }

    @Override
    public final DefaultMailMessageBuilder newMailBuilder() throws MailBuilderException {
	return new DefaultMailMessageBuilder(this);
    }

    synchronized final Transport getTransportConnected() throws MailSendException {
	if (!transport.isConnected())
	    try {
		try {
		    transport.connect();
		} catch (final AuthenticationFailedException e) {
		    if (username == null || password == null)
			throw e;
		    transport.connect(username, password);
		}
	    } catch (final MessagingException e) {
		throw senderWrapException(e);
	    }
	return transport;
    }

    @Override
    public final synchronized void close() throws Exception {
	if (!transport.isConnected())
	    return;
	transport.close();
    }
}
