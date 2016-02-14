package com.lapsa.mailutil.impl;

import java.nio.charset.Charset;

import com.lapsa.mailutil.MailMessageHTMLPart;

class MailMessageHTMLPartImpl implements MailMessageHTMLPart {

    private Charset charset;
    private String html;
    private String contentId;

    MailMessageHTMLPartImpl(String html, Charset charset, String contentId) {
	this.charset = charset;
	this.html = html;
	this.contentId = contentId;
    }

    MailMessageHTMLPartImpl(String html, Charset charset) {
	this.charset = charset;
	this.html = html;
    }

    public String getHTML() {
	return html;
    }

    public Charset getCharset() {
	return charset;
    }

    @Override
    public String getContentID() {
	return contentId;
    }

}
