package com.lapsa.mailutil.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageStreamPart;

class MultiPartStreamProvider implements MultiPartProvider {
    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	final MimeBodyPart result = new MimeBodyPart();

	final MailMessageStreamPart mmsp = (MailMessageStreamPart) part;

	final DataSource ds = new DataSource() {
	    @Override
	    public OutputStream getOutputStream() throws IOException {
		throw new IOException("Is not writable data source");
	    }

	    @Override
	    public String getName() {
		return mmsp.getName();
	    }

	    @Override
	    public InputStream getInputStream() throws IOException {
		return mmsp.getInputStream();
	    }

	    @Override
	    public String getContentType() {
		return mmsp.getContentType();
	    }
	};
	final DataHandler dh = new DataHandler(ds);
	result.setDataHandler(dh);
	return result;
    }
}
