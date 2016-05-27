package com.lapsa.mailutil.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import org.w3c.dom.Document;

import com.lapsa.mailutil.MailAddress;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessage;
import com.lapsa.mailutil.MailMessageBuilder;
import com.lapsa.mailutil.MailMessageByteArrayPart;
import com.lapsa.mailutil.MailMessageFilePart;
import com.lapsa.mailutil.MailMessageHTMLPart;
import com.lapsa.mailutil.MailMessageStreamPart;
import com.lapsa.mailutil.MailMessageTextPart;
import com.lapsa.mailutil.MailMessageURResourcePart;
import com.lapsa.mailutil.MailMessageXMLPart;

public class MailMessageBuilderImpl implements MailMessageBuilder {
    @Override
    public MailMessageFilePart createFilePart(File file) throws MailException {
	return new MailMessageFilePartImpl(file);
    }

    @Override
    public MailMessageFilePart createFilePart(File file, String contentId) throws MailException {
	return new MailMessageFilePartImpl(file, contentId);
    }

    @Override
    public MailMessageURResourcePart createURLResourcePart(URL urlResource) throws MailException {
	return new MailMessageURLPartImpl(urlResource);
    }

    @Override
    public MailMessageURResourcePart createURLResourcePart(URL urlResource, String contentId) throws MailException {
	return new MailMessageURLPartImpl(urlResource, contentId);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html) throws MailException {
	return new MailMessageHTMLPartImpl(html, Charset.defaultCharset());
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html, String contentId) throws MailException {
	return new MailMessageHTMLPartImpl(html, Charset.defaultCharset(), contentId);
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
    public MailMessageTextPart createTextPart(String text, String contentId) throws MailException {
	return new MailMessageTextPartImpl(text, Charset.defaultCharset(), contentId);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html, Charset charset) throws MailException {
	return new MailMessageHTMLPartImpl(html, charset);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(String html, Charset charset, String contentId) throws MailException {
	return new MailMessageHTMLPartImpl(html, charset, contentId);
    }

    @Override
    public MailMessageTextPart createTextPart(String text, Charset charset) throws MailException {
	return new MailMessageTextPartImpl(text, charset);
    }

    @Override
    public MailMessageTextPart createTextPart(String text, Charset charset, String contentId) throws MailException {
	return new MailMessageTextPartImpl(text, charset, contentId);
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc) throws MailException {
	return new MailMessageXMLPartImpl(doc, Charset.defaultCharset());
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc, String contentId) throws MailException {
	return new MailMessageXMLPartImpl(doc, Charset.defaultCharset(), contentId);
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc, Charset charset) throws MailException {
	return new MailMessageXMLPartImpl(doc, charset);
    }

    @Override
    public MailMessageXMLPart createXMLPart(Document doc, Charset charset, String contentId) throws MailException {
	return new MailMessageXMLPartImpl(doc, charset, contentId);
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
    public MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream,
	    String contentId) throws MailException, IOException {
	return new MailMessageStreamPartImpl(name, contentType, inputStream, contentId);
    }

    @Override
    public MailMessageByteArrayPart createByteArrayPart(String name, String contentType, byte[] bytes)
	    throws MailException, IOException {
	return new MailMessageByteArrayPartImpl(name, contentType, bytes);
    }

    @Override
    public MailMessageByteArrayPart createByteArrayPart(String name, String contentType, byte[] bytes, String contentId)
	    throws MailException, IOException {
	return new MailMessageByteArrayPartImpl(name, contentType, bytes, contentId);
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
    public MailMessage createMessage(MailAddress from, MailAddress to, String subject) throws MailException {
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
    public MailMessage createMessage(MailAddress to, String subject, Charset charset) throws MailException {
	return createMessage(null, to, subject, charset);
    }
}
