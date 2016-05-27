package com.lapsa.mailutil.impl;

import java.io.File;
import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageURResourcePart;

class MultiPartURLResourceProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageURResourcePart p = (MailMessageURResourcePart) part;

	File file = new File(p.getURLResource().getFile());
	try {
	    result.attachFile(file);
	    if (p.getContentID() != null)
		result.setContentID(p.getContentID());
	    return result;
	} catch (IOException e) {
	    throw new MessagingException("Unable to attach file", e);
	}
    }
}
