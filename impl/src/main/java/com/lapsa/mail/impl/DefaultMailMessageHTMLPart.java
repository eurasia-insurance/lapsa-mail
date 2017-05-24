package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessageHTMLPart;

final class DefaultMailMessageHTMLPart extends AMailMessagePart implements MailMessageHTMLPart {
    private static final long serialVersionUID = -6192479830058334804L;

    final Charset charset;
    final String html;

    DefaultMailMessageHTMLPart(final DefaultMailService service, final String html, final Charset charset) {
	this(service, html, charset, null);
    }

    DefaultMailMessageHTMLPart(final DefaultMailService service, final String html, final Charset charset,
	    final String contentId) {
	super(service, contentId);
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
