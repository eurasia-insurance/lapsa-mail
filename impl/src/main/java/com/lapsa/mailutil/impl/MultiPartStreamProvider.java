package com.lapsa.mailutil.impl;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageStreamPart;

class MultiPartStreamProvider implements MultiPartProvider {
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	MailMessageStreamPart mmsp = (MailMessageStreamPart) part;
	try {
	    DataSource ds = new InputStreamDataSource(mmsp.getName(), mmsp.getInputStream(),
		    mmsp.getContentType());
	    DataHandler dh = new DataHandler(ds);
	    result.setDataHandler(dh);
	    return result;
	} catch (IOException e) {
	    throw new MessagingException("Unable to add stream part", e);
	}
    }
}
