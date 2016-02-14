package com.lapsa.mailutil.impl;

import com.lapsa.mailutil.MailMessageByteArrayPart;

class MailMessageByteArrayPartImpl implements MailMessageByteArrayPart {

    private final String name;
    private final String contentType;
    private final byte[] bytes;
    private String contentId;

    MailMessageByteArrayPartImpl(String name, String contentType, byte[] bytes) {
	this.name = name;
	this.contentType = contentType;
	this.bytes = bytes;
    }

    MailMessageByteArrayPartImpl(String name, String contentType, byte[] bytes, String contentId) {
	this.name = name;
	this.contentType = contentType;
	this.bytes = bytes;
	this.contentId = contentId;
    }

    @Override
    public byte[] getBytes() {
	return bytes;
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
