package com.lapsa.mailutil.impl;

import java.io.IOException;
import java.io.InputStream;

import com.lapsa.mailutil.MailMessageStreamPart;

class MailMessageStreamPartImpl implements MailMessageStreamPart {

    private InputStream inputStream;
    private String contentType;
    private String name;
    private String contentId;

    MailMessageStreamPartImpl(String name, String contentType, InputStream inputStream, String contentId)
	    throws IOException {
	this.name = name;
	this.contentType = contentType;
	this.inputStream = inputStream;
	this.contentId = contentId;
    }

    MailMessageStreamPartImpl(String name, String contentType, InputStream inputStream) throws IOException {
	this.name = name;
	this.contentType = contentType;
	this.inputStream = inputStream;
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
