package com.metrobank.mail.helper.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.metrobank.mail.helper.MailMessagePart;
import com.metrobank.mail.helper.MailMessageXMLPart;
import com.metrobank.xml.DOMUtils;

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