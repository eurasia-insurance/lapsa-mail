package com.lapsa.mail.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lapsa.mail.MailMessageStreamPart;

class DefaultMailMessageStreamPart implements MailMessageStreamPart {

    private final InputStream inputStream;
    private final String contentType;
    private final String name;
    private final String contentId;

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
	this.name = name;
	this.contentType = contentType;
	this.contentId = contentId;
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
    public InputStream getInputStream() throws IOException {
	return inputStream;
    }

    @Override
    public String getContentType() {
	return contentType;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public String getContentID() {
	return contentId;
    }

}
