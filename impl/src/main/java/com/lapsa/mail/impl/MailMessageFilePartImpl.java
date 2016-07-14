package com.lapsa.mail.impl;

import java.io.File;

import com.lapsa.mail.MailMessageFilePart;

class MailMessageFilePartImpl implements MailMessageFilePart {

    private final File file;
    private final String contentId;

    MailMessageFilePartImpl(final File file, final String contentId) {
	this.file = file;
	this.contentId = contentId;
    }

    MailMessageFilePartImpl(final File file) {
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
