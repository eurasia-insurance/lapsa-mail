package com.lapsa.mailhelper.impl;

import java.nio.charset.Charset;

import com.lapsa.mailhelper.MailMessageHTMLPart;

class MailMessageHTMLPartImpl implements MailMessageHTMLPart {

    private Charset charset;
    private String html;

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

}
