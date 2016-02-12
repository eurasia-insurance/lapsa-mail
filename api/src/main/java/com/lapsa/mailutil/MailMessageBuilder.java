package com.lapsa.mailutil;

import java.io.*;
import java.nio.charset.*;

import org.w3c.dom.*;

public interface MailMessageBuilder {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	MailMessage createMessage() throws MailException;

	MailMessage createMessage(Charset charset) throws MailException;

	MailMessage createMessage(String subject) throws MailException;

	MailMessage createMessage(String subject, Charset charset)
			throws MailException;

	MailMessage createMessage(MailAddress to, String subject)
			throws MailException;

	MailMessage createMessage(MailAddress to, String subject, Charset charset)
			throws MailException;

	MailMessage createMessage(MailAddress from, MailAddress to, String subject)
			throws MailException;

	MailMessage createMessage(MailAddress from, MailAddress to, String subject,
			Charset charset) throws MailException;

	MailMessageTextPart createTextPart(String text) throws MailException;

	MailMessageTextPart createTextPart(String text, Charset charset)
			throws MailException;

	MailMessageHTMLPart createHTMLPart(String html) throws MailException;

	MailMessageHTMLPart createHTMLPart(String html, Charset charset)
			throws MailException;

	MailMessagePart createXMLPart(Document doc) throws MailException;

	MailMessagePart createXMLPart(Document doc, Charset charset)
			throws MailException;

	MailMessageFilePart createFilePart(File file) throws MailException;

	MailMessageStreamPart createStreamPart(String name, String contentType,
			InputStream inputStream) throws MailException, IOException;

	MailAddress createAddress(String eMail) throws MailException;

	MailAddress createAddress(String eMail, String name)
			throws MailException;

}
