package com.lapsa.mailutil.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.mail.Session;

import org.w3c.dom.Document;

import com.lapsa.mailutil.InvalidMessageException;
import com.lapsa.mailutil.MailAddress;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessage;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailMessageFilePart;
import com.lapsa.mailutil.MailMessageHTMLPart;
import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageStreamPart;
import com.lapsa.mailutil.MailMessageTextPart;
import com.lapsa.mailutil.MailSender;
import com.lapsa.mailutil.MailService;

public abstract class BaseMailHelperImpl implements MailService {

    protected abstract Session getSession();

    @Override
    public MailSender createSender() throws MailException {
	return new MailSenderImpl(getSession());
    }

    @Override
    public MailMessageBuilder createBuilder() throws MailException {
	return new MailMessageBuilderImpl();
    }

    @Override
    @Deprecated
    public void send(MailMessage message) throws MailException {
	try {
	    createSender().send(message);
	} catch (InvalidMessageException e) {
	    throw new MailException(e);
	}
    }

    @Override
    @Deprecated
    public MailMessage createMessage() throws MailException {
	return createBuilder().createMessage();
    }

    @Override
    @Deprecated
    public MailMessage createMessage(Charset charset)
	    throws MailException {
	return createBuilder().createMessage(charset);
    }

    @Override
    @Deprecated
    public MailMessage createMessage(String subject) throws MailException {
	return createBuilder().createMessage(subject);
    }

    @Override
    @Deprecated
    public MailMessage createMessage(String subject, Charset charset)
	    throws MailException {
	return createBuilder().createMessage(subject, charset);
    }

    @Override
    @Deprecated
    public MailMessage createMessage(MailAddress from, String subject)
	    throws MailException {
	return createBuilder().createMessage(from, null, subject);
    }

    @Override
    @Deprecated
    public MailMessage createMessage(MailAddress from, String subject,
	    Charset charset) throws MailException {
	return createBuilder().createMessage(from, null, subject, charset);
    }

    @Override
    @Deprecated
    public MailMessage createMessage(MailAddress from, MailAddress to,
	    String subject) throws MailException {
	return createBuilder().createMessage(from, to, subject);
    }

    @Override
    @Deprecated
    public MailMessage createMessage(MailAddress from, MailAddress to,
	    String subject, Charset charset) throws MailException {
	return createBuilder().createMessage(from, to, subject, charset);
    }

    @Override
    @Deprecated
    public MailMessageTextPart createTextPart(String text)
	    throws MailException {
	return createBuilder().createTextPart(text);
    }

    @Override
    @Deprecated
    public MailMessageTextPart createTextPart(String text, Charset charset)
	    throws MailException {
	return createBuilder().createTextPart(text);
    }

    @Override
    @Deprecated
    public MailMessageHTMLPart createHTMLPart(String html)
	    throws MailException {
	return createBuilder().createHTMLPart(html);
    }

    @Override
    @Deprecated
    public MailMessageHTMLPart createHTMLPart(String html, Charset charset)
	    throws MailException {
	return createBuilder().createHTMLPart(html, charset);
    }

    @Override
    @Deprecated
    public MailMessagePart createXMLPart(Document doc)
	    throws MailException {
	return createBuilder().createXMLPart(doc);
    }

    @Override
    @Deprecated
    public MailMessagePart createXMLPart(Document doc, Charset charset)
	    throws MailException {
	return createBuilder().createXMLPart(doc, charset);
    }

    @Override
    @Deprecated
    public MailMessageFilePart createFilePart(File file)
	    throws MailException {
	return createBuilder().createFilePart(file);
    }

    @Override
    @Deprecated
    public MailMessageStreamPart createStreamPart(String name,
	    String contentType, InputStream inputStream)
		    throws MailException, IOException {
	return createBuilder().createStreamPart(name, contentType, inputStream);
    }

    @Override
    @Deprecated
    public MailAddress createAddress(String eMail) throws MailException {
	return createBuilder().createAddress(eMail);
    }

    @Override
    @Deprecated
    public MailAddress createAddress(String eMail, String name)
	    throws MailException {
	return createBuilder().createAddress(eMail, name);
    }

}
