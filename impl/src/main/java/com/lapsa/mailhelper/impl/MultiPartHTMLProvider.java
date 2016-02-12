package com.lapsa.mailhelper.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailhelper.MailMessageHTMLPart;
import com.lapsa.mailhelper.MailMessagePart;

class MultiPartHTMLProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	MailMessageHTMLPart mmhp = (MailMessageHTMLPart) part;
	// result.setText(mmhp.getHTML(), "UTF-8", "html");
	result.setText(mmhp.getHTML(), mmhp.getCharset().name(), "html");
	return result;
    }
}