package com.lapsa.mailutil.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mailutil.MailMessageByteArrayPart;
import com.lapsa.mailutil.MailMessagePart;

class MultiPartByteArrayProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();
	MailMessageByteArrayPart p = (MailMessageByteArrayPart) part;
	DataSource source = new ByteArrayDataSource(p.getBytes(), p.getContentType());
	DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	result.setFileName(p.getName());
	if (p.getContentID() != null)
	    result.setContentID(p.getContentID());
	return result;
    }

}
