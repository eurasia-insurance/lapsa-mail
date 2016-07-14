package com.lapsa.mail.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessagePart;
import com.lapsa.mail.MailMessageTextPart;

class MailMessageTextPartMultiPartProvider implements MultiPartProvider {
    @Override
    public BodyPart getBodyPart(final MailMessagePart part) throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	final MailMessageTextPart p = (MailMessageTextPart) part;

	// result.setText(mmtp.getText(), "UTF-8", "plain");
	result.setText(p.getText(), p.getCharset().name(), "plain");
	if (p.getContentID() != null)
	    result.setContentID(String.format("<%1$s>", p.getContentID()));
	return result;
    }
}
