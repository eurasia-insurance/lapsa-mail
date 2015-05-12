package com.metrobank.mail.helper.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.metrobank.mail.helper.MailMessagePart;
import com.metrobank.mail.helper.MailMessageTextPart;

class MultiPartTextProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	MailMessageTextPart mmtp = (MailMessageTextPart) part;
	// result.setText(mmtp.getText(), "UTF-8", "plain");
	result.setText(mmtp.getText(), mmtp.getCharset().name(), "plain");
	return result;
    }
}
