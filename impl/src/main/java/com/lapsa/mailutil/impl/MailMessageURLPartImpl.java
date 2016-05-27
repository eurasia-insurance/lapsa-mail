package com.lapsa.mailutil.impl;

import java.net.URL;

import com.lapsa.mailutil.MailMessageURResourcePart;

class MailMessageURLPartImpl implements MailMessageURResourcePart {

    private final URL urlResource;
    private final String contentId;

    MailMessageURLPartImpl(final URL urlResource) {
	this.urlResource = urlResource;
	this.contentId = null;
    }

    MailMessageURLPartImpl(final URL urlResource, final String contentId) {
	this.urlResource = urlResource;
	this.contentId = contentId;
    }

    @Override
    public String getContentID() {
	return contentId;
    }

    @Override
    public URL getURLResource() {
	return urlResource;
    }
}
