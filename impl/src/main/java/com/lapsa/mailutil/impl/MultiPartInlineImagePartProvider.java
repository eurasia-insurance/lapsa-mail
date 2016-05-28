package com.lapsa.mailutil.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mailutil.MailMessageInlineImagePart;
import com.lapsa.mailutil.MailMessagePart;

public class MultiPartInlineImagePartProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageInlineImagePart p = (MailMessageInlineImagePart) part;
	DataSource source = new ByteArrayDataSource(p.getByteArray(), p.getContentType());
	DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	result.setFileName(p.getFileName());
	result.setContentID(String.format("<%1$s>", p.getContentID() != null ? p.getContentID() : p.getFileName()));
	result.setDisposition(MimeBodyPart.INLINE);
	return result;
    }
}
