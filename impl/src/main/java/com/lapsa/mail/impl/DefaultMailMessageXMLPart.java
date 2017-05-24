package com.lapsa.mail.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import org.w3c.dom.Document;

import com.lapsa.mail.MailMessageXMLPart;

final class DefaultMailMessageXMLPart extends AMailMessagePart implements MailMessageXMLPart {
    private static final long serialVersionUID = 7713991325528136449L;

    final Charset charset;
    final Document doc;

    DefaultMailMessageXMLPart(final DefaultMailService service, final Document doc, final Charset charset) {
	this(service, doc, charset, null);
    }

    DefaultMailMessageXMLPart(final DefaultMailService service, final Document doc, final Charset charset,
	    final String contentId) {
	super(service, contentId);
	this.doc = doc;
	this.charset = charset;
    }

    @Override
    public BodyPart getBodyPart() throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();
	String content;
	try {
	    content = DOMUtils.getInstance().getAsString(doc, charset.name());
	} catch (final UnsupportedEncodingException e) {
	    throw new MessagingException("Unsupported encoding '" + charset.name() + "'", e);
	}
	result.setText(content, charset.name(), "xml");
	putContentId(result);
	return result;
    }
}
