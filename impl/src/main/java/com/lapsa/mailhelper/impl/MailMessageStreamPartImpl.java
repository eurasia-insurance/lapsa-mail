package com.lapsa.mailhelper.impl;

import java.io.IOException;
import java.io.InputStream;

import com.lapsa.mailhelper.MailMessageStreamPart;

class MailMessageStreamPartImpl implements MailMessageStreamPart {

    private InputStream inputStream;
    private String contentType;
    private String name;

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

}
