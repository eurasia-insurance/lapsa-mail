package tech.lapsa.lapsa.mail.impl;

import java.util.Set;

import tech.lapsa.lapsa.mail.MailBuilderException;
import tech.lapsa.lapsa.mail.MailSendException;

interface Checks {

    static MailSendException senderWrapException(final Exception e) {
	return new MailSendException(String.format("Error sending message: %1$s", e.getMessage()), e);
    }

    static <T> T builderRequireNonNull(final T obj, final String message) throws MailBuilderException {
	if (obj == null)
	    throw new MailBuilderException(message);
	return obj;
    }

    static <T> T builderRequireNotIn(final T obj, final Set<T> set, final String message) throws MailBuilderException {
	if (set.contains(obj))
	    throw new MailBuilderException(message);
	return obj;
    }

    static MailBuilderException builderWrapException(final Exception e) {
	return builderWrapException(e, e.getMessage());
    }

    static MailBuilderException builderWrapException(final Exception e, final String message) {
	return new MailBuilderException(String.format("Error while building message: %1$s", message), e);
    }

}
