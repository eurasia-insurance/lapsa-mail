package com.lapsa.mailutil.impl;

import java.io.IOException;
import java.io.InputStream;

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
	MimeBodyPart result = new MimeBodyPart();
	MailMessageStreamPart p = (MailMessageStreamPart) part;

	try (InputStream is = p.getInputStream()) {
	    DataSource source = new ByteArrayDataSource(is, p.getContentType());
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
