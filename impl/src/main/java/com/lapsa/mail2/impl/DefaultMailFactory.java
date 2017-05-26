package com.lapsa.mail2.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;

import java.nio.charset.Charset;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import com.lapsa.mail2.MailMessageBuilder;
import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.MailSendException;

class DefaultMailFactory implements MailFactory {

    final Session session;

    private Transport transport;

    final MailAddress alwaysBlindCopyTo;
    final MailAddress forwardAllMailTo;

    final Charset defaultCharset;
    final MailAddress defaultRecipient;

    final MailAddress defaultSender;

    DefaultMailFactory(final Session session, Charset defaultCharset, MailAddress alwaysBlindCopyTo,
	    MailAddress forwardAllMailTo, MailAddress defaultRecipient, MailAddress defaultSender) {
	this.session = Objects.requireNonNull(session, "Session can not be null");
	this.defaultCharset = Objects.requireNonNull(defaultCharset, "Charset can not be null");
	this.alwaysBlindCopyTo = alwaysBlindCopyTo;
	this.forwardAllMailTo = forwardAllMailTo;
	this.defaultRecipient = defaultRecipient;
	this.defaultSender = defaultSender;
    }

    @Override
    public final MailMessageBuilder newMailBuilder() throws MailBuilderException {
	return new DefaultMailMessageBuilder(this, defaultCharset, defaultSender, defaultRecipient, alwaysBlindCopyTo,
		forwardAllMailTo);
    }

    synchronized final Transport getTransportConnected() throws MailSendException {
	try {
	    if (transport == null)
		transport = session.getTransport();
	    if (!transport.isConnected())
		transport.connect(session.getProperty(MAIL_USER), session.getProperty(MAIL_PASSWORD));
	    return transport;
	} catch (MessagingException e) {
	    throw senderWrapException(e);
	}
    }

    static final MailSendException senderWrapException(Exception e) {
	return new MailSendException(String.format("Error sending message: %1$s", e.getMessage()), e);
    }

    @Override
    public final synchronized void close() throws Exception {
	if (transport == null)
	    return;
	if (!transport.isConnected())
	    return;
	transport.close();
    }
}
