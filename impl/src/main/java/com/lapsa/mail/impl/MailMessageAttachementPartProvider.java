package com.lapsa.mail.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessageAttachementPart;
import com.lapsa.mail.MailMessagePart;

public class MailMessageAttachementPartProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageAttachementPart p = (MailMessageAttachementPart) part;
	DataSource source = new ByteArrayDataSource(p.getContents(), p.getContentType());
	DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	result.setFileName(p.getAttachementFileName());
	result.setContentID(
		String.format("<%1$s>", p.getContentID() != null ? p.getContentID() : p.getAttachementFileName()));
	switch (p.getType()) {
	case INLINE:
	    result.setDisposition(MimeBodyPart.INLINE);
	    break;
	case ATTACHEMENT:
	default:
	    result.setDisposition(MimeBodyPart.ATTACHMENT);
	    break;
	}
	return result;
    }
}
