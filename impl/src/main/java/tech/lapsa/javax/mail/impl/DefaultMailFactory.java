package tech.lapsa.javx.mail.impl;

import static tech.lapsa.javx.mail.impl.Checks.*;

import java.nio.charset.Charset;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.javx.mail.MailBuilderException;
import tech.lapsa.javx.mail.MailFactory;
import tech.lapsa.javx.mail.MailSendException;

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
	this.defaultCharset = MyObjects.requireNonNull(factoryBuilder.defaultCharset, "Charset can not be null");
	this.alwaysBlindCopyTo = factoryBuilder.alwaysBlindCopyTo;
	this.forwardAllMailTo = factoryBuilder.forwardAllMailTo;
	this.defaultRecipient = factoryBuilder.defaultRecipient;
	this.defaultSender = factoryBuilder.defaultSender;

	this.username = factoryBuilder.username;
	this.password = factoryBuilder.password;

	try {
	    this.transport = session.getTransport();
	} catch (NoSuchProviderException e) {
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
		} catch (AuthenticationFailedException e) {
		    if (username == null || password == null)
			throw e;
		    transport.connect(username, password);
		}
	    } catch (MessagingException e) {
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
