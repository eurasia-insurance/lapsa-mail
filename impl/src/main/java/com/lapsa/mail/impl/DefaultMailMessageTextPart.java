package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import com.lapsa.mail.MailMessageTextPart;

class MailMessageTextPartImpl implements MailMessageTextPart {

    private final String text;
    private final Charset charset;
    private final String contentId;

    MailMessageTextPartImpl(final String text, final Charset charset, final String contentId) {
	this.text = text;
	this.charset = charset;
	this.contentId = contentId;
    }

    MailMessageTextPartImpl(final String text, final Charset charset) {
	this.text = text;
	this.charset = charset;
	contentId = null;
    }

    @Override
    public String getText() {
	return text;
    }

    @Override
    public Charset getCharset() {
	return charset;
    }

    @Override
    public String getContentID() {
	return contentId;
    }
}
