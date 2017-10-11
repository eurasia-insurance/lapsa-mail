package tech.lapsa.javx.mail.impl;

import static tech.lapsa.javx.mail.impl.Checks.*;

import javax.mail.Session;

import tech.lapsa.javx.mail.MailBuilderException;
import tech.lapsa.javx.mail.MailFactory;
import tech.lapsa.javx.mail.MailMessageBuilder;

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
