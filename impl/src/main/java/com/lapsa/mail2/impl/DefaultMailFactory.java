package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import java.nio.charset.Charset;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.MailMessageBuilder;
import com.lapsa.mail2.MailSendException;

final class DefaultMailFactory implements MailFactory {

    final Session session;
    final Transport transport;

    final MailAddress alwaysBlindCopyTo;
    final MailAddress forwardAllMailTo;

    final Charset defaultCharset;
    final MailAddress defaultRecipient;

    final MailAddress defaultSender;

    DefaultMailFactory(final DefaultMailFactoryBuilder factoryBuilder,
	    final Session session)
	    throws MailBuilderException {
	this.session = Objects.requireNonNull(session, "Session can not be null");
	this.defaultCharset = Objects.requireNonNull(factoryBuilder.defaultCharset, "Charset can not be null");
	this.alwaysBlindCopyTo = factoryBuilder.alwaysBlindCopyTo;
	this.forwardAllMailTo = factoryBuilder.forwardAllMailTo;
	this.defaultRecipient = factoryBuilder.defaultRecipient;
	this.defaultSender = factoryBuilder.defaultSender;

	try {
	    this.transport = session.getTransport();
	} catch (NoSuchProviderException e) {
	    throw builderWrapException(e);
	}
    }

    @Override
    public final MailMessageBuilder newMailBuilder() throws MailBuilderException {
	return new DefaultMailMessageBuilder(this);
    }

    synchronized final Transport getTransportConnected() throws MailSendException {
	try {
	    if (!transport.isConnected())
		transport.connect();
	    return transport;
	} catch (MessagingException e) {
	    throw senderWrapException(e);
	}
    }

    synchronized final Transport getSessionConnected() throws MailSendException {
	try {
	    if (!transport.isConnected())
		transport.connect();
	    return transport;
	} catch (MessagingException e) {
	    throw senderWrapException(e);
	}
    }

    @Override
    public final synchronized void close() throws Exception {
	if (!transport.isConnected())
	    return;
	transport.close();
    }
}
