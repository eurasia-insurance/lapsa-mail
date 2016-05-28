package com.lapsa.mailutil.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lapsa.mailutil.MailMessageInlineImagePart;

class MailMessageInlineImagePartImpl implements MailMessageInlineImagePart {

    private final String contentType;
    private final String fileName;
    private final String contentId;
    private final byte[] bytes;

    MailMessageInlineImagePartImpl(String contentType, byte[] bytes, String fileName,
	    String contentId) {
	this.contentType = contentType;
	this.fileName = fileName;
	this.contentId = contentId;
	this.bytes = bytes;
    }

    @Override
    public String getContentID() {
	return contentId;
    }

    @Override
    public String getContentType() {
	return contentType;
    }

    @Override
    public InputStream getNewInputStream() {
	return new ByteArrayInputStream(bytes);
    }

    @Override
    public String getFileName() {
	return fileName;
    }

    @Override
    public byte[] getByteArray() {
	return bytes.clone();
    }
}
