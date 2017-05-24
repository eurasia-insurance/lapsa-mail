package com.lapsa.mail.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail.MailMessageStreamPart;

final class DefaultMailMessageStreamPart extends DefaultMailMessagePart implements MailMessageStreamPart {

    final InputStream inputStream;
    final String contentType;
    final String name;

    DefaultMailMessageStreamPart(final String name, final String contentType, final InputStream inputStream,
	    final String contentId)
	    throws IOException {
	this(name, contentType, inputStream, false, contentId);
    }

    DefaultMailMessageStreamPart(final String name, final String contentType, final InputStream inputStream)
	    throws IOException {
	this(name, contentType, inputStream, false, null);
    }

    DefaultMailMessageStreamPart(final String name, final String contentType, final InputStream inputStream,
	    final boolean immediatlyRead) throws IOException {
	this(name, contentType, inputStream, immediatlyRead, null);
    }

    DefaultMailMessageStreamPart(final String name, final String contentType, final InputStream inputStream,
	    final boolean readImmediately,
	    final String contentId) throws IOException {
	super(contentId);
	this.name = name;
	this.contentType = contentType;
	if (readImmediately) {
	    final ByteArrayOutputStream bais = new ByteArrayOutputStream();
	    int readed = -1;
	    final byte[] buff = new byte[256];
	    while ((readed = inputStream.read(buff)) != -1)
		bais.write(buff, 0, readed);
	    this.inputStream = new ByteArrayInputStream(bais.toByteArray());
	} else
	    this.inputStream = inputStream;
    }

    @Override
    public BodyPart getBodyPart() throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();

	try (InputStream is = inputStream) {
	    final DataSource source = new ByteArrayDataSource(is, contentType);
	    final DataHandler dh = new DataHandler(source);
	    result.setDataHandler(dh);
	    if (name != null)
		result.setFileName(name);
	    putContentId(result);
	    return result;
	} catch (final IOException e) {
	    throw new MessagingException(e.getMessage(), e);
	}
    }
}
