package com.lapsa.mailutil.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageByteArrayPart;

class MultiPartByteArrayProvider implements MultiPartProvider {

    @Override
    public BodyPart getBodyPart(MailMessagePart part) throws MessagingException {
	MimeBodyPart result = new MimeBodyPart();

	final MailMessageByteArrayPart mmsp = (MailMessageByteArrayPart) part;
	final ByteArrayInputStream bais = new ByteArrayInputStream(mmsp.getBytes());
	DataSource ds = new DataSource() {
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
		return bais;
	    }

	    @Override
	    public String getContentType() {
		return mmsp.getContentType();
	    }
	};
	DataHandler dh = new DataHandler(ds);
	result.setDataHandler(dh);
	return result;
    }

}
