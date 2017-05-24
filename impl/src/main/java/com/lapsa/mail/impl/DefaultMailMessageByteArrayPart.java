package com.lapsa.mail.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessageByteArrayPart;

final class DefaultMailMessageByteArrayPart extends AMailMessagePart implements MailMessageByteArrayPart {
    private static final long serialVersionUID = -6121847538061202388L;

    final String name;
    final String contentType;
    final byte[] bytes;

    DefaultMailMessageByteArrayPart(final DefaultMailService service, final String name, final String contentType,
	    final byte[] bytes) {
	this(service, name, contentType, bytes, null);
    }

    DefaultMailMessageByteArrayPart(final DefaultMailService service, final String name, final String contentType,
	    final byte[] bytes,
	    final String contentId) {
	super(service, contentId);
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
