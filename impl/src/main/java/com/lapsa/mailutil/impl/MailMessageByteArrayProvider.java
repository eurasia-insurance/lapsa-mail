package com.lapsa.mailutil.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mailutil.MailMessageByteArrayPart;
import com.lapsa.mailutil.MailMessagePart;

class MailMessageByteArrayProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageByteArrayPart p = (MailMessageByteArrayPart) part;

	DataSource source = new ByteArrayDataSource(p.getBytes(), p.getContentType());
	DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	if (p.getName() != null)
	    result.setFileName(p.getName());
	if (p.getContentID() != null)
	    result.setContentID(String.format("<%1$s>", p.getContentID()));
	return result;
    }

}
