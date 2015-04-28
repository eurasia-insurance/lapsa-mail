package com.metrobank.mail.helper.impl;

import java.io.IOException;
import java.io.InputStream;

import com.metrobank.mail.helper.MailMessageStreamPart;

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
