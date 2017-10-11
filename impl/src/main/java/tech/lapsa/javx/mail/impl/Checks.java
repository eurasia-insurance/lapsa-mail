package tech.lapsa.javx.mail.impl;

import java.util.Set;

import tech.lapsa.javx.mail.MailBuilderException;
import tech.lapsa.javx.mail.MailSendException;

interface Checks {

    static MailSendException senderWrapException(Exception e) {
	return new MailSendException(String.format("Error sending message: %1$s", e.getMessage()), e);
    }

    static <T> T builderRequireNonNull(T obj, String message) throws MailBuilderException {
	if (obj == null)
	    throw new MailBuilderException(message);
	return obj;
    }

    static <T> T builderRequireNotIn(T obj, Set<T> set, String message) throws MailBuilderException {
	if (set.contains(obj))
	    throw new MailBuilderException(message);
	return obj;
    }

    static MailBuilderException builderWrapException(Exception e) {
	return builderWrapException(e, e.getMessage());
    }

    static MailBuilderException builderWrapException(Exception e, String message) {
	return new MailBuilderException(String.format("Error while building message: %1$s", message), e);
    }

}
