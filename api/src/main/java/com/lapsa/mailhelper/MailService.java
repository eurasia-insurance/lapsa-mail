package com.lapsa.mailhelper;

import java.io.*;
import java.nio.charset.*;

import org.w3c.dom.*;

public interface MailService {
	@Deprecated
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	@Deprecated
	MailMessage createMessage() throws MailHelperException;

	@Deprecated
	MailMessage createMessage(Charset charset) throws MailHelperException;

	@Deprecated
	MailMessage createMessage(String subject) throws MailHelperException;

	@Deprecated
	MailMessage createMessage(String subject, Charset charset)
			throws MailHelperException;

	@Deprecated
	MailMessage createMessage(MailAddress from, String subject)
			throws MailHelperException;

	@Deprecated
	MailMessage createMessage(MailAddress from, String subject, Charset charset)
			throws MailHelperException;

	@Deprecated
	MailMessage createMessage(MailAddress from, MailAddress to, String subject)
			throws MailHelperException;

	@Deprecated
	MailMessage createMessage(MailAddress from, MailAddress to, String subject,
			Charset charset) throws MailHelperException;

	@Deprecated
	MailMessageTextPart createTextPart(String text) throws MailHelperException;

	@Deprecated
	MailMessageTextPart createTextPart(String text, Charset charset)
			throws MailHelperException;

	@Deprecated
	MailMessageHTMLPart createHTMLPart(String html) throws MailHelperException;

	@Deprecated
	MailMessageHTMLPart createHTMLPart(String html, Charset charset)
			throws MailHelperException;

	@Deprecated
	MailMessagePart createXMLPart(Document doc) throws MailHelperException;

	@Deprecated
	MailMessagePart createXMLPart(Document doc, Charset charset)
			throws MailHelperException;

	@Deprecated
	MailMessageFilePart createFilePart(File file) throws MailHelperException;

	@Deprecated
	MailMessageStreamPart createStreamPart(String name, String contentType,
			InputStream inputStream) throws MailHelperException, IOException;

	@Deprecated
	MailAddress createAddress(String eMail) throws MailHelperException;

	@Deprecated
	MailAddress createAddress(String eMail, String name)
			throws MailHelperException;

	MailSender createSender() throws MailHelperException;

	MailMessageBuilder createBuilder() throws MailHelperException;

	@Deprecated
	void send(MailMessage message) throws MailHelperException;
}
