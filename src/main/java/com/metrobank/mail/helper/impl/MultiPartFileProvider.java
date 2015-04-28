package com.metrobank.mail.helper.impl;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.metrobank.mail.helper.MailMessageFilePart;
import com.metrobank.mail.helper.MailMessagePart;

class MultiPartFileProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	MailMessageFilePart mmfp = (MailMessageFilePart) part;
	try {
	    result.attachFile(mmfp.getFile());
	    return result;
	} catch (IOException e) {
	    throw new MessagingException("Unable to attach file", e);
	}
    }
}
