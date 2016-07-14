package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import com.lapsa.mail.MailMessageHTMLPart;

class DefaultMailMessageHTMLPart implements MailMessageHTMLPart {

    private final Charset charset;
    private final String html;
    private final String contentId;

    DefaultMailMessageHTMLPart(final String html, final Charset charset, final String contentId) {
	this.charset = charset;
	this.html = html;
	this.contentId = contentId;
    }

    DefaultMailMessageHTMLPart(final String html, final Charset charset) {
	this.charset = charset;
	this.html = html;
	contentId = null;
    }

    @Override
    public String getHTML() {
	return html;
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
