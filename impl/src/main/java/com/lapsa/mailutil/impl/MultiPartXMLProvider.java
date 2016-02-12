package com.lapsa.mailutil.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageXMLPart;

class MultiPartXMLProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	MailMessageXMLPart mmxp = (MailMessageXMLPart) part;
	String content;
	try {
	    content = DOMUtils.getInstance().getAsString(mmxp.getDocument(), mmxp.getCharset().name());
	} catch (UnsupportedEncodingException e) {
	    throw new MessagingException("Unsupported encoding '" + mmxp.getCharset().name() + "'", e);
	}
	result.setText(content, mmxp.getCharset().name(), "xml");
	return result;
    }
}