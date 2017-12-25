package tech.lapsa.lapsa.mail.impl;

import static tech.lapsa.lapsa.mail.impl.Checks.*;

import javax.mail.Session;

import tech.lapsa.lapsa.mail.MailBuilderException;
import tech.lapsa.lapsa.mail.MailFactory;
import tech.lapsa.lapsa.mail.MailMessageBuilder;

public final class SessionMailFactory implements MailFactory {

    final DefaultMailFactory delegate;

    public SessionMailFactory(final Session session) throws MailBuilderException {
	builderRequireNonNull(session, "Session can not be null");
	final DefaultMailFactoryBuilder d = new DefaultMailFactoryBuilder();
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
