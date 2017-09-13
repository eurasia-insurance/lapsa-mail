package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import javax.mail.Session;

import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailFactory;
import com.lapsa.mail2.MailMessageBuilder;

public final class SessionMailFactory implements MailFactory {

    final DefaultMailFactory delegate;

    public SessionMailFactory(final Session session) throws MailBuilderException {
	builderRequireNonNull(session, "Session can not be null");
	DefaultMailFactoryBuilder d = new DefaultMailFactoryBuilder();
	if (session.getProperties() != null)
	    d.withProperties(session.getProperties());
	delegate = d.build(session);
    }

    @Override
    public void close() throws Exception {
	delegate.close();
    }

    @Override
    public MailMessageBuilder newMailBuilder() throws MailBuilderException {
	return delegate.newMailBuilder();
    }
}
