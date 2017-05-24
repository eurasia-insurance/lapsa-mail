package com.lapsa.mail.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.AttachementType;
import com.lapsa.mail.MailMessageAttachementPart;

final class DefaultMailMessageAttachementPart extends AMailMessagePart implements MailMessageAttachementPart {

    final String contentType;
    final String fileName;
    final byte[] bytes;
    final AttachementType type;

    DefaultMailMessageAttachementPart(final DefaultMailService service, final String contentType, final byte[] bytes,
	    final String fileName,
	    final AttachementType type) {
	this(service, contentType, bytes, fileName, null, type);
    }

    DefaultMailMessageAttachementPart(final DefaultMailService service, final String contentType, final byte[] bytes,
	    final String fileName,
	    final String contentId, final AttachementType type) {
	super(service, contentId);
	this.contentType = contentType;
	this.fileName = fileName;
	this.bytes = bytes;
	this.type = type;
    }

    @Override
    public BodyPart getBodyPart() throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	final DataSource source = new ByteArrayDataSource(bytes.clone(), contentType);
	final DataHandler dh = new DataHandler(source);
	result.setDataHandler(dh);
	result.setFileName(fileName);

	putContentId(result, fileName);

	switch (type) {
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
