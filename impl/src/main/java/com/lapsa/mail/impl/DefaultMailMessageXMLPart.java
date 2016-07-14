package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import org.w3c.dom.Document;

import com.lapsa.mail.MailMessageXMLPart;

class MailMessageXMLPartImpl implements MailMessageXMLPart {

    private final Charset charset;
    private final Document doc;
    private final String contentId;

    MailMessageXMLPartImpl(final Document doc, final Charset charset) {
	this.doc = doc;
	this.charset = charset;
	contentId = null;
    }

    MailMessageXMLPartImpl(final Document doc, final Charset charset, final String contentId) {
	this.doc = doc;
	this.charset = charset;
	this.contentId = contentId;
    }

    @Override
    public Charset getCharset() {
	return charset;
    }

    @Override
    public Document getDocument() {
	return doc;
    }

    @Override
    public String getContentID() {
	return contentId;
    }
}
