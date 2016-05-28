package com.lapsa.mailutil.impl;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailutil.MailMessageFilePart;
import com.lapsa.mailutil.MailMessagePart;

class MultiPartFileProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageFilePart p = (MailMessageFilePart) part;

	try {
	    result.attachFile(p.getFile());
	    if (p.getContentID() != null)
		result.setContentID(String.format("<%1$s>", p.getContentID()));
	    return result;
	} catch (IOException e) {
	    throw new MessagingException("Unable to attach file", e);
	}
    }
}
