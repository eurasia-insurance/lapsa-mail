package com.lapsa.mail.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessageByteArrayPart;
import com.lapsa.mail.MailMessagePart;

class MailMessageByteArrayMultiPartProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(final MailMessagePart part) throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	final MailMessageByteArrayPart p = (MailMessageByteArrayPart) part;

	final DataSource source = new ByteArrayDataSource(p.getBytes(), p.getContentType());
	final DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	if (p.getName() != null)
	    result.setFileName(p.getName());
	if (p.getContentID() != null)
	    result.setContentID(String.format("<%1$s>", p.getContentID()));
	return result;
    }

}
