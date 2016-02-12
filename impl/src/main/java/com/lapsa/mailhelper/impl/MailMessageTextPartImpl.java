package com.lapsa.mailhelper.impl;

import java.nio.charset.Charset;

import com.lapsa.mailhelper.MailMessageTextPart;

class MailMessageTextPartImpl implements MailMessageTextPart {

    private String text;
    private Charset charset;

    MailMessageTextPartImpl(String text, Charset charset) {
	this.text = text;
	this.charset = charset;
    }

    public String getText() {
	return text;
    }

    public Charset getCharset() {
	return charset;
    }
}
