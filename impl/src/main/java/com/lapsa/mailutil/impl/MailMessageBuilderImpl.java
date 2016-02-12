package com.lapsa.mailutil.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.w3c.dom.Document;

import com.lapsa.mailutil.MailAddress;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessage;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailMessageFilePart;
import com.lapsa.mailutil.MailMessageHTMLPart;
import com.lapsa.mailutil.MailMessageStreamPart;
import com.lapsa.mailutil.MailMessageTextPart;
import com.lapsa.mailutil.MailMessageXMLPart;

public class MailMessageBuilderImpl implements MailMessageBuilder {
    @Override
    public MailMessageFilePart createFilePart(File file) throws MailException {
	return new MailMessageFilePartImpl(file);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html) throws MailException {
	return new MailMessageHTMLPartImpl(html, Charset.defaultCharset());
    }

    @Override
    public MailAddress createAddress(String eMail, String name) throws MailException {
	return new MailAddressImpl(eMail, name);
    }

    @Override
    public MailMessageTextPart createTextPart(String text) throws MailException {
	return new MailMessageTextPartImpl(text, Charset.defaultCharset());
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html, Charset charset) throws MailException {
	return new MailMessageHTMLPartImpl(html, charset);
    }

    @Override
    public MailMessageTextPart createTextPart(String text, Charset charset) throws MailException {
	return new MailMessageTextPartImpl(text, charset);
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc) throws MailException {
	return new MailMessageXMLPartImpl(doc, Charset.defaultCharset());
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc, Charset charset) throws MailException {
	return new MailMessageXMLPartImpl(doc, charset);
    }

    @Override
    public MailAddress createAddress(String eMail) throws MailException {
	return new MailAddressImpl(eMail, "");
    }

    @Override
    public MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream)
	    throws MailException, IOException {
	return new MailMessageStreamPartImpl(name, contentType, inputStream);
    }

    @Override
    public MailMessage createMessage() throws MailException {
	return createMessage(null, null, null, null);
    }

    @Override
    public MailMessage createMessage(Charset charset) throws MailException {
	return createMessage(null, null, null, charset);
    }

    @Override
    public MailMessage createMessage(String subject) throws MailException {
	return createMessage(null, null, subject, null);
    }

    @Override
    public MailMessage createMessage(MailAddress from, MailAddress to, String subject)
	    throws MailException {
	return createMessage(from, to, subject, null);
    }

    @Override
    public MailMessage createMessage(String subject, Charset charset) throws MailException {
	return createMessage(null, null, subject, charset);
    }

    @Override
    public MailMessage createMessage(MailAddress from, MailAddress to, String subject, Charset charset)
	    throws MailException {
	MailMessage mm = new MailMessageImpl();
	if (from != null)
	    mm.setFrom(from);
	if (to != null)
	    mm.addTORecipient(to);
	if (subject != null)
	    mm.setSubject(subject);
	if (charset != null)
	    mm.setCharset(charset);
	return mm;
    }

    @Override
    public MailMessage createMessage(MailAddress to, String subject) throws MailException {
	return createMessage(null, to, subject, null);
    }

    @Override
    public MailMessage createMessage(MailAddress to, String subject, Charset charset)
	    throws MailException {
	return createMessage(null, to, subject, charset);
    }
}
