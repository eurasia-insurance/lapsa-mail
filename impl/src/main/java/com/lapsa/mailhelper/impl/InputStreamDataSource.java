package com.lapsa.mailhelper.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

class InputStreamDataSource implements DataSource {

    private String name;
    private InputStream inputStream;
    private String contentType;

    InputStreamDataSource(String name, InputStream inputStream, String contentType) {
	this.name = name;
	this.inputStream = inputStream;
	this.contentType = contentType;
    }

    public String getContentType() {
	return contentType;
    }

    public InputStream getInputStream() throws IOException {
	return inputStream;
    }

    public String getName() {
	return name;
    }

    public OutputStream getOutputStream() throws IOException {
	throw new IOException("Is not writable data source");
    }

}