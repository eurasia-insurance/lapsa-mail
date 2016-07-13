package com.lapsa.mailutil.impl;

import java.io.File;

import com.lapsa.mailutil.MailMessageFilePart;

class MailMessageFilePartImpl implements MailMessageFilePart {

    private File file;
    private String contentId;

    MailMessageFilePartImpl(File file, String contentId) {
	this.file = file;
	this.contentId = contentId;
    }

    MailMessageFilePartImpl(File file) {
	this.file = file;
    }

    public File getFile() {
	return file;
    }

    @Override
    public String getContentID() {
	return contentId;
    }
}
