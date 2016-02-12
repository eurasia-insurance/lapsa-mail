package com.lapsa.mailhelper.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.mail.Session;

import org.w3c.dom.Document;

import com.lapsa.mailhelper.*;

public abstract class BaseMailHelperImpl implements MailService {

	protected abstract Session getSession();

	@Override
	public MailSender createSender() throws MailHelperException {
		return new MailSenderImpl(getSession());
	}

	@Override
	public MailMessageBuilder createBuilder() throws MailHelperException {
		return new MailMessageBuilderImpl();
	}

	@Override
	@Deprecated
	public void send(MailMessage message) throws MailHelperException {
		try {
			createSender().send(message);
		} catch (InvalidMessageException e) {
			throw new MailHelperException(e);
		}
	}

	@Override
	@Deprecated
	public MailMessage createMessage() throws MailHelperException {
		return createBuilder().createMessage();
	}

	@Override
	@Deprecated
	public MailMessage createMessage(Charset charset)
			throws MailHelperException {
		return createBuilder().createMessage(charset);
	}

	@Override
	@Deprecated
	public MailMessage createMessage(String subject) throws MailHelperException {
		return createBuilder().createMessage(subject);
	}

	@Override
	@Deprecated
	public MailMessage createMessage(String subject, Charset charset)
			throws MailHelperException {
		return createBuilder().createMessage(subject, charset);
	}

	@Override
	@Deprecated
	public MailMessage createMessage(MailAddress from, String subject)
			throws MailHelperException {
		return createBuilder().createMessage(from, null, subject);
	}

	@Override
	@Deprecated
	public MailMessage createMessage(MailAddress from, String subject,
			Charset charset) throws MailHelperException {
		return createBuilder().createMessage(from, null, subject, charset);
	}

	@Override
	@Deprecated
	public MailMessage createMessage(MailAddress from, MailAddress to,
			String subject) throws MailHelperException {
		return createBuilder().createMessage(from, to, subject);
	}

	@Override
	@Deprecated
	public MailMessage createMessage(MailAddress from, MailAddress to,
			String subject, Charset charset) throws MailHelperException {
		return createBuilder().createMessage(from, to, subject, charset);
	}

	@Override
	@Deprecated
	public MailMessageTextPart createTextPart(String text)
			throws MailHelperException {
		return createBuilder().createTextPart(text);
	}

	@Override
	@Deprecated
	public MailMessageTextPart createTextPart(String text, Charset charset)
			throws MailHelperException {
		return createBuilder().createTextPart(text);
	}

	@Override
	@Deprecated
	public MailMessageHTMLPart createHTMLPart(String html)
			throws MailHelperException {
		return createBuilder().createHTMLPart(html);
	}

	@Override
	@Deprecated
	public MailMessageHTMLPart createHTMLPart(String html, Charset charset)
			throws MailHelperException {
		return createBuilder().createHTMLPart(html, charset);
	}

	@Override
	@Deprecated
	public MailMessagePart createXMLPart(Document doc)
			throws MailHelperException {
		return createBuilder().createXMLPart(doc);
	}

	@Override
	@Deprecated
	public MailMessagePart createXMLPart(Document doc, Charset charset)
			throws MailHelperException {
		return createBuilder().createXMLPart(doc, charset);
	}

	@Override
	@Deprecated
	public MailMessageFilePart createFilePart(File file)
			throws MailHelperException {
		return createBuilder().createFilePart(file);
	}

	@Override
	@Deprecated
	public MailMessageStreamPart createStreamPart(String name,
			String contentType, InputStream inputStream)
			throws MailHelperException, IOException {
		return createBuilder().createStreamPart(name, contentType, inputStream);
	}

	@Override
	@Deprecated
	public MailAddress createAddress(String eMail) throws MailHelperException {
		return createBuilder().createAddress(eMail);
	}

	@Override
	@Deprecated
	public MailAddress createAddress(String eMail, String name)
			throws MailHelperException {
		return createBuilder().createAddress(eMail, name);
	}

}
