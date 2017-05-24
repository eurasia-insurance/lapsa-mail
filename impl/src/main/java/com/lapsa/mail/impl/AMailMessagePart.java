package com.lapsa.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessagePart;

abstract class AMailMessagePart implements MailMessagePart, MultiPartProvider {

    final transient DefaultMailService service;

    final String contentId;

    AMailMessagePart(final DefaultMailService service, final String contentId) {
	this.service = service;
	this.contentId = contentId;
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
