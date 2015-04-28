package com.metrobank.mail.helper.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.metrobank.mail.helper.MailMessageHTMLPart;
import com.metrobank.mail.helper.MailMessagePart;

class MultiPartHTMLProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	MailMessageHTMLPart mmhp = (MailMessageHTMLPart) part;
	// result.setText(mmhp.getHTML(), "UTF-8", "html");
	result.setText(mmhp.getHTML(), mmhp.getCharset().name(), "html");
	return result;
    }
}