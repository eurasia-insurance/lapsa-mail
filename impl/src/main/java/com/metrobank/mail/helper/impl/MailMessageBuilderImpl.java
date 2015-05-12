package com.metrobank.mail.helper.impl;

import java.io.*;
import java.nio.charset.*;

import org.w3c.dom.*;

import com.metrobank.mail.helper.*;

public class MailMessageBuilderImpl implements MailMessageBuilder {
    @Override
    public MailMessageFilePart createFilePart(File file) throws MailHelperException {
	return new MailMessageFilePartImpl(file);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html) throws MailHelperException {
	return new MailMessageHTMLPartImpl(html, Charset.defaultCharset());
    }

    @Override
    public MailAddress createAddress(String eMail, String name) throws MailHelperException {
	return new MailAddressImpl(eMail, name);
    }

    @Override
    public MailMessageTextPart createTextPart(String text) throws MailHelperException {
	return new MailMessageTextPartImpl(text, Charset.defaultCharset());
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html, Charset charset) throws MailHelperException {
	return new MailMessageHTMLPartImpl(html, charset);
    }

    @Override
    public MailMessageTextPart createTextPart(String text, Charset charset) throws MailHelperException {
	return new MailMessageTextPartImpl(text, charset);
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc) throws MailHelperException {
	return new MailMessageXMLPartImpl(doc, Charset.defaultCharset());
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc, Charset charset) throws MailHelperException {
	return new MailMessageXMLPartImpl(doc, charset);
    }

    @Override
    public MailAddress createAddress(String eMail) throws MailHelperException {
	return new MailAddressImpl(eMail, "");
    }

    @Override
    public MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream)
	    throws MailHelperException, IOException {
	return new MailMessageStreamPartImpl(name, contentType, inputStream);
    }

    @Override
    public MailMessage createMessage() throws MailHelperException {
	return createMessage(null, null, null, null);
    }

    @Override
    public MailMessage createMessage(Charset charset) throws MailHelperException {
	return createMessage(null, null, null, charset);
    }

    @Override
    public MailMessage createMessage(String subject) throws MailHelperException {
	return createMessage(null, null, subject, null);
    }

    @Override
    public MailMessage createMessage(MailAddress from, MailAddress to, String subject)
	    throws MailHelperException {
	return createMessage(from, to, subject, null);
    }

    @Override
    public MailMessage createMessage(String subject, Charset charset) throws MailHelperException {
	return createMessage(null, null, subject, charset);
    }

    @Override
    public MailMessage createMessage(MailAddress from, MailAddress to, String subject, Charset charset)
	    throws MailHelperException {
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
    public MailMessage createMessage(MailAddress to, String subject) throws MailHelperException {
	return createMessage(null, to, subject, null);
    }

    @Override
    public MailMessage createMessage(MailAddress to, String subject, Charset charset)
	    throws MailHelperException {
	return createMessage(null, to, subject, charset);
    }
}
