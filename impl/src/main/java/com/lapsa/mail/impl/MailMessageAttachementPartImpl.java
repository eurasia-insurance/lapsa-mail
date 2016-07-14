package com.lapsa.mail.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lapsa.mail.AttachementType;
import com.lapsa.mail.MailMessageAttachementPart;

class MailMessageAttachementPartImpl implements MailMessageAttachementPart {

    private final String contentType;
    private final String fileName;
    private final String contentId;
    private final byte[] bytes;

    private final AttachementType type;

    MailMessageAttachementPartImpl(final String contentType, final byte[] bytes, final String fileName,
	    final String contentId, final AttachementType type) {
	this.contentType = contentType;
	this.fileName = fileName;
	this.contentId = contentId;
	this.bytes = bytes;
	this.type = type;
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
    public InputStream getNewContentsInputStream() {
	return new ByteArrayInputStream(bytes);
    }

    @Override
    public String getAttachementFileName() {
	return fileName;
    }

    @Override
    public byte[] getContents() {
	return bytes.clone();
    }

    @Override
    public AttachementType getType() {
	return type;
    }
}
