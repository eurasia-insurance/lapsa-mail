package com.lapsa.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessagePart;

abstract class DefaultMailMessagePart implements MailMessagePart, MultiPartProvider {

    final String contentId;

    DefaultMailMessagePart(String contentId) {
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

    private static void _putContentId(final MimeBodyPart mimeBodyPart, final String contentId) throws MessagingException {
	mimeBodyPart.setContentID(String.format("<%1$s>", contentId));
    }

}
