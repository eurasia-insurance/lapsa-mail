package com.lapsa.mail2.impl;

import javax.mail.Session;

import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.MailFactoryBuilder;
import com.lapsa.mail2.MailMessageBuilder;

public final class SessionMailFactory implements MailFactory {

    final MailFactory delegate;

    public SessionMailFactory(final Session session) throws MailBuilderException {
	delegate = MailFactoryBuilder
		.newMailFactoryBuilder(DefaultMailFactoryBuilder.class)
		.withProperties(session.getProperties())
		.build();
    }

    @Override
    public  void close() throws Exception {
	delegate.close();
    }

    @Override
    public MailMessageBuilder newMailBuilder() throws MailBuilderException {
	return delegate.newMailBuilder();
    }
}
