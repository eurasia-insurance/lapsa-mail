package com.lapsa.mail.impl;

import java.io.File;

import com.lapsa.mail.MailMessageFilePart;

class DefaultMailMessageFilePart implements MailMessageFilePart {

    private final File file;
    private final String contentId;

    DefaultMailMessageFilePart(final File file, final String contentId) {
	this.file = file;
	this.contentId = contentId;
    }

    DefaultMailMessageFilePart(final File file) {
	this.file = file;
	contentId = null;
    }

    @Override
    public File getFile() {
	return file;
    }

    @Override
    public String getContentID() {
	return contentId;
    }
}
