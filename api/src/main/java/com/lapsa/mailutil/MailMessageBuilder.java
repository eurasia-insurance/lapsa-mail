package com.lapsa.mailutil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.w3c.dom.Document;

public interface MailMessageBuilder {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    MailMessage createMessage() throws MailException;

    MailMessage createMessage(Charset charset) throws MailException;

    MailMessage createMessage(String subject) throws MailException;

    MailMessage createMessage(String subject, Charset charset) throws MailException;

    MailMessage createMessage(MailAddress to, String subject) throws MailException;

    MailMessage createMessage(MailAddress to, String subject, Charset charset) throws MailException;

    MailMessage createMessage(MailAddress from, MailAddress to, String subject) throws MailException;

    MailMessage createMessage(MailAddress from, MailAddress to, String subject, Charset charset) throws MailException;

    MailMessageTextPart createTextPart(String text) throws MailException;

    MailMessageTextPart createTextPart(String text, String contentId) throws MailException;

    MailMessageTextPart createTextPart(String text, Charset charset) throws MailException;

    MailMessageTextPart createTextPart(String text, Charset charset, String contentId) throws MailException;

    MailMessageHTMLPart createHTMLPart(String html) throws MailException;

    MailMessageHTMLPart createHTMLPart(String html, String contentId) throws MailException;

    MailMessageHTMLPart createHTMLPart(String html, Charset charset) throws MailException;

    MailMessageHTMLPart createHTMLPart(String html, Charset charset, String contentId) throws MailException;

    MailMessagePart createXMLPart(Document doc) throws MailException;

    MailMessagePart createXMLPart(Document doc, String contentId) throws MailException;

    MailMessagePart createXMLPart(Document doc, Charset charset) throws MailException;

    MailMessagePart createXMLPart(Document doc, Charset charset, String contentId) throws MailException;

    MailMessageFilePart createFilePart(File file) throws MailException;

    MailMessageFilePart createFilePart(File file, String contentId) throws MailException;

    MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream)
	    throws MailException, IOException;

    MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream, boolean readImmediately)
	    throws MailException, IOException;

    MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream, String contentId)
	    throws MailException, IOException;

    MailMessageStreamPart createStreamPart(String name, String contentType, InputStream inputStream, boolean readImmediately, String contentId)
	    throws MailException, IOException;

    MailMessageByteArrayPart createByteArrayPart(String name, String contentType, byte[] bytes)
	    throws MailException, IOException;

    MailMessageByteArrayPart createByteArrayPart(String name, String contentType, byte[] bytes, String contentId)
	    throws MailException, IOException;

    MailAddress createAddress(String eMail) throws MailException;

    MailAddress createAddress(String eMail, String name) throws MailException;

    /*
     * createInlineImagePart methods
     */

    /*
     * source File
     */

    MailMessageInlineImagePart createInlineImagePart(String contentType, File file) throws MailException, IOException;

    MailMessageInlineImagePart createInlineImagePart(String contentType, File file, String contentId)
	    throws MailException, IOException;

    /*
     * source InputStream
     */
    MailMessageInlineImagePart createInlineImagePart(String contentType, InputStream inputStream, String fileName)
	    throws MailException, IOException;

    MailMessageInlineImagePart createInlineImagePart(String contentType, InputStream inputStream, String fileName,
	    String contentId)
	    throws MailException, IOException;
}
