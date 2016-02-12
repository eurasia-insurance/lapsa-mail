package com.lapsa.mailhelper.impl;

import java.io.File;

import com.lapsa.mailhelper.MailMessageFilePart;

class MailMessageFilePartImpl implements MailMessageFilePart {

    private File file;

    MailMessageFilePartImpl(File file) {
	this.file = file;
    }

    public File getFile() {
	return file;
    }
}
