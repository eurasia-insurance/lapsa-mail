package com.lapsa.mail.impl;

import java.nio.charset.Charset;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mail.MailMessageTextPart;

final class DefaultMailMessageTextPart extends AMailMessagePart implements MailMessageTextPart {

    final String text;
    final Charset charset;

    DefaultMailMessageTextPart(final DefaultMailService service, final String text, final Charset charset) {
	this(service, text, charset, null);
    }

    DefaultMailMessageTextPart(final DefaultMailService service, final String text, final Charset charset,
	    final String contentId) {
	super(service, contentId);
	this.text = text;
	this.charset = charset;
    }

    @Override
    public BodyPart getBodyPart() throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	// result.setText(mmtp.getText(), "UTF-8", "plain");
	result.setText(text, charset.name(), "plain");
	putContentId(result);
	return result;
    }
}
