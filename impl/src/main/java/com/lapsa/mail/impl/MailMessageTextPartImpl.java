package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import com.lapsa.mail.MailMessageTextPart;

class MailMessageTextPartImpl implements MailMessageTextPart {

    private String text;
    private Charset charset;
    private String contentId;

    MailMessageTextPartImpl(String text, Charset charset, String contentId) {
	this.text = text;
	this.charset = charset;
	this.contentId = contentId;
    }

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

    @Override
    public String getContentID() {
	return contentId;
    }
}
