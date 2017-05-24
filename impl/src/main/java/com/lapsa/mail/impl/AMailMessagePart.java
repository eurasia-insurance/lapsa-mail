package com.lapsa.mail.impl;

import java.io.Serializable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.lapsa.mail.MailMessagePart;

abstract class AMailMessagePart implements MailMessagePart, MultiPartProvider, Serializable {
    private static final long serialVersionUID = -2790508720253272737L;

    final transient DefaultMailService service;

    final String contentId;

    AMailMessagePart(final DefaultMailService service, final String contentId) {
	this.service = service;
	this.contentId = contentId;
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(final Object other) {
	return EqualsBuilder.reflectionEquals(this, other, false);
    }

    final void putContentId(final MimeBodyPart mimeBodyPart, final String defaultContentId) throws MessagingException {
	if (contentId != null)
	    _putContentId(mimeBodyPart, contentId);
	else if (defaultContentId != null)
	    _putContentId(mimeBodyPart, defaultContentId);
    }

    final void putContentId(final MimeBodyPart mimeBodyPart) throws MessagingException {
	putContentId(mimeBodyPart, null);
    }

    // PRIVATE

    private static void _putContentId(final MimeBodyPart mimeBodyPart, final String contentId)
	    throws MessagingException {
	mimeBodyPart.setContentID(String.format("<%1$s>", contentId));
    }

}
