package com.lapsa.mailutil.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lapsa.mailutil.AttachementType;
import com.lapsa.mailutil.MailMessageAttachementPart;

class MailMessageAttachementPartImpl implements MailMessageAttachementPart {

    private final String contentType;
    private final String fileName;
    private final String contentId;
    private final byte[] bytes;

    private final AttachementType type;

    MailMessageAttachementPartImpl(String contentType, byte[] bytes, String fileName,
	    String contentId, AttachementType type) {
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

    public AttachementType getType() {
	return type;
    }
}
