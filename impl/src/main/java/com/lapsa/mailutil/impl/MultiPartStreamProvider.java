package com.lapsa.mailutil.impl;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageStreamPart;

class MultiPartStreamProvider implements MultiPartProvider {
    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	try {
	    MimeBodyPart result = new MimeBodyPart();
	    MailMessageStreamPart p = (MailMessageStreamPart) part;
	    DataSource source = new ByteArrayDataSource(p.getInputStream(), p.getContentType());
	    DataHandler dh = new DataHandler(source);
	    result.setDataHandler(dh);
	    result.setFileName(p.getName());
	    if (p.getContentID() != null)
		result.setContentID(p.getContentID());
	    return result;
	} catch (IOException e) {
	    throw new MessagingException(e.getMessage(), e);
	}
    }
}
