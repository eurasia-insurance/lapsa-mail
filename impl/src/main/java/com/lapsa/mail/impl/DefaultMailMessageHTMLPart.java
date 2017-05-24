package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessageHTMLPart;

final class DefaultMailMessageHTMLPart extends AMailMessagePart implements MailMessageHTMLPart {

    final Charset charset;
    final String html;

    DefaultMailMessageHTMLPart(final String html, final Charset charset) {
	this(html, charset, null);
    }

    DefaultMailMessageHTMLPart(final String html, final Charset charset, final String contentId) {
	super(contentId);
	this.charset = charset;
	this.html = html;
    }

    @Override
    public BodyPart getBodyPart() throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	result.setText(html, charset.name(), "html");
	putContentId(result);
	return result;
    }

}
