package com.lapsa.mail.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessagePart;
import com.lapsa.mail.MailMessageStreamPart;

class MailMessageStreamPartProvider implements MultiPartProvider {
    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageStreamPart p = (MailMessageStreamPart) part;

	try (InputStream is = p.getInputStream()) {
	    DataSource source = new ByteArrayDataSource(is, p.getContentType());
	    DataHandler dh = new DataHandler(source);
	    result.setDataHandler(dh);
	    if (p.getName() != null)
		result.setFileName(p.getName());
	    if (p.getContentID() != null)
		result.setContentID(String.format("<%1$s>", p.getContentID()));
	    return result;
	} catch (IOException e) {
	    throw new MessagingException(e.getMessage(), e);
	}
    }
}
