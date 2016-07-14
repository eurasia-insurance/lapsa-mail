package com.lapsa.mail.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessageHTMLPart;
import com.lapsa.mail.MailMessagePart;

class MailMessageHTMLPartMultiPartProvider implements MultiPartProvider {
    @Override
    public BodyPart getBodyPart(final MailMessagePart part) throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	final MailMessageHTMLPart p = (MailMessageHTMLPart) part;

	result.setText(p.getHTML(), p.getCharset().name(), "html");
	if (p.getContentID() != null)
	    result.setContentID(String.format("<%1$s>", p.getContentID()));
	return result;
    }
}