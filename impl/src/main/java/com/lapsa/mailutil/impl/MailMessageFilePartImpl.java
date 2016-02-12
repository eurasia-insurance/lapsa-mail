package com.lapsa.mailutil.impl;

import java.io.File;

import com.lapsa.mailutil.MailMessageFilePart;

class MailMessageFilePartImpl implements MailMessageFilePart {

    private File file;

    MailMessageFilePartImpl(File file) {
	this.file = file;
    }

    public File getFile() {
	return file;
    }
}
