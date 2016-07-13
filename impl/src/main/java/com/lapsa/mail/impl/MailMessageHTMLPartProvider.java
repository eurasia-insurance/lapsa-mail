package com.lapsa.mail.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessageHTMLPart;
import com.lapsa.mail.MailMessagePart;

class MailMessageHTMLPartProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageHTMLPart p = (MailMessageHTMLPart) part;

	result.setText(p.getHTML(), p.getCharset().name(), "html");
	if (p.getContentID() != null)
	    result.setContentID(String.format("<%1$s>", p.getContentID()));
	return result;
    }
}