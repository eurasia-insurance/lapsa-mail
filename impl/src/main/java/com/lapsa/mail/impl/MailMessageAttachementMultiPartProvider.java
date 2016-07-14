package com.lapsa.mail.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessageAttachementPart;
import com.lapsa.mail.MailMessagePart;

public class MailMessageAttachementMultiPartProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(final MailMessagePart part) throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	final MailMessageAttachementPart p = (MailMessageAttachementPart) part;
	final DataSource source = new ByteArrayDataSource(p.getContents(), p.getContentType());
	final DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	result.setFileName(p.getAttachementFileName());
	result.setContentID(
		String.format("<%1$s>", p.getContentID() != null ? p.getContentID() : p.getAttachementFileName()));
	switch (p.getType()) {
	case INLINE:
	    result.setDisposition(Part.INLINE);
	    break;
	case ATTACHEMENT:
	default:
	    result.setDisposition(Part.ATTACHMENT);
	    break;
	}
	return result;
    }
}
