package com.lapsa.mail.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessageByteArrayPart;

final class DefaultMailMessageByteArrayPart extends DefaultMailMessagePart
	implements MailMessageByteArrayPart, MultiPartProvider {

    final String name;
    final String contentType;
    final byte[] bytes;

    DefaultMailMessageByteArrayPart(final String name, final String contentType, final byte[] bytes) {
	this(name, contentType, bytes, null);
    }

    DefaultMailMessageByteArrayPart(final String name, final String contentType, final byte[] bytes,
	    final String contentId) {
	super(contentId);
	this.name = name;
	this.contentType = contentType;
	this.bytes = bytes;
    }

    @Override
    public BodyPart getBodyPart() throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();

	final DataSource source = new ByteArrayDataSource(bytes, contentType);
	final DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	if (name != null)
	    result.setFileName(name);
	putContentId(result);
	return result;
    }
}
