package com.lapsa.mail.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lapsa.mail.MailMessageStreamPart;

class MailMessageStreamPartImpl implements MailMessageStreamPart {

    private final InputStream inputStream;
    private final String contentType;
    private final String name;
    private final String contentId;

    MailMessageStreamPartImpl(String name, String contentType, InputStream inputStream, String contentId)
	    throws IOException {
	this(name, contentType, inputStream, false, contentId);
    }

    MailMessageStreamPartImpl(String name, String contentType, InputStream inputStream) throws IOException {
	this(name, contentType, inputStream, false, null);
    }

    MailMessageStreamPartImpl(String name, String contentType, InputStream inputStream, boolean immediatlyRead) throws IOException {
	this(name, contentType, inputStream, immediatlyRead, null);
    }

    MailMessageStreamPartImpl(String name, String contentType, InputStream inputStream, boolean readImmediately,
	    String contentId) throws IOException {
	this.name = name;
	this.contentType = contentType;
	this.contentId = contentId;
	if (readImmediately) {
	    ByteArrayOutputStream bais = new ByteArrayOutputStream();
	    int readed = -1;
	    byte[] buff = new byte[256];
	    while ((readed = inputStream.read(buff))!=-1)
		bais.write(buff, 0, readed);
	    this.inputStream = new ByteArrayInputStream(bais.toByteArray());
	} else {
	    this.inputStream = inputStream;
	}
    }

    public InputStream getInputStream() throws IOException {
	return inputStream;
    }

    public String getContentType() {
	return contentType;
    }

    public String getName() {
	return name;
    }

    @Override
    public String getContentID() {
	return contentId;
    }

}
