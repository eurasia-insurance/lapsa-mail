package com.metrobank.mail.helper.impl;

import java.io.File;

import com.metrobank.mail.helper.MailMessageFilePart;

class MailMessageFilePartImpl implements MailMessageFilePart {

    private File file;

    MailMessageFilePartImpl(File file) {
	this.file = file;
    }

    public File getFile() {
	return file;
    }
}
