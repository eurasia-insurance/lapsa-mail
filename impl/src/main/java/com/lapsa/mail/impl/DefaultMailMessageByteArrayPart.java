package com.lapsa.mail.impl;

import com.lapsa.mail.MailMessageByteArrayPart;

class MailMessageByteArrayPartImpl implements MailMessageByteArrayPart {

    private final String name;
    private final String contentType;
    private final byte[] bytes;
    private final String contentId;

    MailMessageByteArrayPartImpl(final String name, final String contentType, final byte[] bytes) {
	this.name = name;
	this.contentType = contentType;
	this.bytes = bytes;
	contentId = null;
    }

    MailMessageByteArrayPartImpl(final String name, final String contentType, final byte[] bytes,
	    final String contentId) {
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
