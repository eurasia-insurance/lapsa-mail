package com.lapsa.mailutil;

import java.io.*;
import java.nio.charset.*;

import org.w3c.dom.*;

public interface MailService {
	@Deprecated
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	@Deprecated
	MailMessage createMessage() throws MailException;

	@Deprecated
	MailMessage createMessage(Charset charset) throws MailException;

	@Deprecated
	MailMessage createMessage(String subject) throws MailException;

	@Deprecated
	MailMessage createMessage(String subject, Charset charset)
			throws MailException;

	@Deprecated
	MailMessage createMessage(MailAddress from, String subject)
			throws MailException;

	@Deprecated
	MailMessage createMessage(MailAddress from, String subject, Charset charset)
			throws MailException;

	@Deprecated
	MailMessage createMessage(MailAddress from, MailAddress to, String subject)
			throws MailException;

	@Deprecated
	MailMessage createMessage(MailAddress from, MailAddress to, String subject,
			Charset charset) throws MailException;

	@Deprecated
	MailMessageTextPart createTextPart(String text) throws MailException;

	@Deprecated
	MailMessageTextPart createTextPart(String text, Charset charset)
			throws MailException;

	@Deprecated
	MailMessageHTMLPart createHTMLPart(String html) throws MailException;

	@Deprecated
	MailMessageHTMLPart createHTMLPart(String html, Charset charset)
			throws MailException;

	@Deprecated
	MailMessagePart createXMLPart(Document doc) throws MailException;

	@Deprecated
	MailMessagePart createXMLPart(Document doc, Charset charset)
			throws MailException;

	@Deprecated
	MailMessageFilePart createFilePart(File file) throws MailException;

	@Deprecated
	MailMessageStreamPart createStreamPart(String name, String contentType,
			InputStream inputStream) throws MailException, IOException;

	@Deprecated
	MailAddress createAddress(String eMail) throws MailException;

	@Deprecated
	MailAddress createAddress(String eMail, String name)
			throws MailException;

	MailSender createSender() throws MailException;

	MailMessageBuilder createBuilder() throws MailException;

	@Deprecated
	void send(MailMessage message) throws MailException;
}
