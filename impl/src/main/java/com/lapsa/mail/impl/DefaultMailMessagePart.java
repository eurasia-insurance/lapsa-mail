package com.lapsa.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessagePart;

abstract class DefaultMailMessagePart implements MailMessagePart, MultiPartProvider {

    final String contentId;

    DefaultMailMessagePart(String contentId) {
	this.contentId = contentId;
    }

    final void putContentId(final MimeBodyPart result, final String defaultContentId) throws MessagingException {
	if (contentId != null)
	    _putContentId(result, contentId);
	else if (defaultContentId != null)
	    _putContentId(result, defaultContentId);
    }

    final void putContentId(final MimeBodyPart result) throws MessagingException {
	putContentId(result, null);
    }

    // PRIVATE

    private static void _putContentId(final MimeBodyPart result, final String contentId) throws MessagingException {
	result.setContentID(String.format("<%1$s>", contentId));
    }

}
