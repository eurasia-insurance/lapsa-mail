package com.metrobank.mail.helper;

import java.io.*;
import java.nio.charset.*;

import org.w3c.dom.*;

public interface MailMessageBuilder {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	MailMessage createMessage() throws MailHelperException;

	MailMessage createMessage(Charset charset) throws MailHelperException;

	MailMessage createMessage(String subject) throws MailHelperException;

	MailMessage createMessage(String subject, Charset charset)
			throws MailHelperException;

	MailMessage createMessage(MailAddress to, String subject)
			throws MailHelperException;

	MailMessage createMessage(MailAddress to, String subject, Charset charset)
			throws MailHelperException;

	MailMessage createMessage(MailAddress from, MailAddress to, String subject)
			throws MailHelperException;

	MailMessage createMessage(MailAddress from, MailAddress to, String subject,
			Charset charset) throws MailHelperException;

	MailMessageTextPart createTextPart(String text) throws MailHelperException;

	MailMessageTextPart createTextPart(String text, Charset charset)
			throws MailHelperException;

	MailMessageHTMLPart createHTMLPart(String html) throws MailHelperException;

	MailMessageHTMLPart createHTMLPart(String html, Charset charset)
			throws MailHelperException;

	MailMessagePart createXMLPart(Document doc) throws MailHelperException;

	MailMessagePart createXMLPart(Document doc, Charset charset)
			throws MailHelperException;

	MailMessageFilePart createFilePart(File file) throws MailHelperException;

	MailMessageStreamPart createStreamPart(String name, String contentType,
			InputStream inputStream) throws MailHelperException, IOException;

	MailAddress createAddress(String eMail) throws MailHelperException;

	MailAddress createAddress(String eMail, String name)
			throws MailHelperException;

}
