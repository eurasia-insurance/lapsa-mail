package com.lapsa.mail.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.w3c.dom.Document;

import com.lapsa.mail.AttachementType;
import com.lapsa.mail.MailAddress;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessage;
import com.lapsa.mail.MailMessageAttachementPart;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailMessageByteArrayPart;
import com.lapsa.mail.MailMessageFilePart;
import com.lapsa.mail.MailMessageHTMLPart;
import com.lapsa.mail.MailMessageStreamPart;
import com.lapsa.mail.MailMessageTextPart;
import com.lapsa.mail.MailMessageXMLPart;

final class DefaultMailMessageBuilder implements MailMessageBuilder {

    final transient DefaultMailService service;

    DefaultMailMessageBuilder(final DefaultMailService service) {
	this.service = service;
    }

    /*
     * createAddress methods
     */
    @Override
    public MailAddress createAddress(final String eMail) throws MailException {
	return new DefaultMailAddress(service, eMail, "");
    }

    @Override
    public MailAddress createAddress(final String smtpAddress, final String friendlyName) throws MailException {
	return new DefaultMailAddress(service, smtpAddress, friendlyName);
    }

    /*
     * createMessage methods
     */
    @Override
    public MailMessage createMessage() throws MailException {
	return createMessage(null, null, null, null);
    }

    @Override
    public MailMessage createMessage(final Charset charset) throws MailException {
	return createMessage(null, null, null, charset);
    }

    @Override
    public MailMessage createMessage(final String subject) throws MailException {
	return createMessage(null, null, subject, null);
    }

    @Override
    public MailMessage createMessage(final MailAddress from, final MailAddress to, final String subject)
	    throws MailException {
	return createMessage(from, to, subject, null);
    }

    @Override
    public MailMessage createMessage(final String subject, final Charset charset) throws MailException {
	return createMessage(null, null, subject, charset);
    }

    @Override
    public MailMessage createMessage(final MailAddress from, final MailAddress to, final String subject,
	    final Charset charset)
	    throws MailException {
	final MailMessage mm = new DefaultMailMessage(service);
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
    public MailMessage createMessage(final MailAddress to, final String subject) throws MailException {
	return createMessage(null, to, subject, null);
    }

    @Override
    public MailMessage createMessage(final MailAddress to, final String subject, final Charset charset)
	    throws MailException {
	return createMessage(null, to, subject, charset);
    }

    /*
     * createTextPart methods
     */
    @Override
    public MailMessageTextPart createTextPart(final String text) throws MailException {
	return new DefaultMailMessageTextPart(service, text, Charset.defaultCharset());
    }

    @Override
    public MailMessageTextPart createTextPart(final String text, final String contentId) throws MailException {
	return new DefaultMailMessageTextPart(service, text, Charset.defaultCharset(), contentId);
    }

    @Override
    public MailMessageTextPart createTextPart(final String text, final Charset charset) throws MailException {
	return new DefaultMailMessageTextPart(service, text, charset);
    }

    @Override
    public MailMessageTextPart createTextPart(final String text, final Charset charset, final String contentId)
	    throws MailException {
	return new DefaultMailMessageTextPart(service, text, charset, contentId);
    }

    @Override
    public MailMessageTextPart createTextPart(final Exception e) throws MailException {
	final StringWriter sw = new StringWriter();
	final PrintWriter pw = new PrintWriter(sw);
	e.printStackTrace(pw);
	return createTextPart(sw.toString());
    }

    @Override
    public MailMessageTextPart createTextPart(final Exception e, final String contentId) throws MailException {
	final StringWriter sw = new StringWriter();
	final PrintWriter pw = new PrintWriter(sw);
	e.printStackTrace(pw);
	return createTextPart(sw.toString(), contentId);
    }

    /*
     * createHTMLPart methods
     */
    @Override
    public MailMessageHTMLPart createHTMLPart(final String html) throws MailException {
	return new DefaultMailMessageHTMLPart(service, html, Charset.defaultCharset());
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(final String html, final String contentId) throws MailException {
	return new DefaultMailMessageHTMLPart(service, html, Charset.defaultCharset(), contentId);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(final String html, final Charset charset) throws MailException {
	return new DefaultMailMessageHTMLPart(service, html, charset);
    }

    @Override
    public MailMessageHTMLPart createHTMLPart(final String html, final Charset charset, final String contentId)
	    throws MailException {
	return new DefaultMailMessageHTMLPart(service, html, charset, contentId);
    }

    /*
     * createXMLPart methods
     */
    @Override
    public MailMessageXMLPart createXMLPart(final Document doc) throws MailException {
	return new DefaultMailMessageXMLPart(service, doc, Charset.defaultCharset());
    }

    @Override
    public MailMessageXMLPart createXMLPart(final Document doc, final String contentId) throws MailException {
	return new DefaultMailMessageXMLPart(service, doc, Charset.defaultCharset(), contentId);
    }

    @Override
    public MailMessageXMLPart createXMLPart(final Document doc, final Charset charset) throws MailException {
	return new DefaultMailMessageXMLPart(service, doc, charset);
    }

    @Override
    public MailMessageXMLPart createXMLPart(final Document doc, final Charset charset, final String contentId)
	    throws MailException {
	return new DefaultMailMessageXMLPart(service, doc, charset, contentId);
    }

    /*
     * createFilePart methods
     */

    @Override
    public MailMessageFilePart createFilePart(final File file) throws MailException {
	return new DefaultMailMessageFilePart(service, file);
    }

    @Override
    public MailMessageFilePart createFilePart(final File file, final String contentId) throws MailException {
	return new DefaultMailMessageFilePart(service, file, contentId);
    }

    /*
     * createStreamPart methods
     */
    @Override
    public MailMessageStreamPart createStreamPart(final String name, final String contentType,
	    final InputStream inputStream)
	    throws MailException, IOException {
	return new DefaultMailMessageStreamPart(service, name, contentType, inputStream);
    }

    @Override
    public MailMessageStreamPart createStreamPart(final String name, final String contentType,
	    final InputStream inputStream,
	    final boolean readImmediately) throws MailException, IOException {
	return new DefaultMailMessageStreamPart(service, name, contentType, inputStream, readImmediately);
    }

    @Override
    public MailMessageStreamPart createStreamPart(final String name, final String contentType,
	    final InputStream inputStream,
	    final String contentId) throws MailException, IOException {
	return new DefaultMailMessageStreamPart(service, name, contentType, inputStream, contentId);
    }

    @Override
    public MailMessageStreamPart createStreamPart(final String name, final String contentType,
	    final InputStream inputStream,
	    final boolean readImmediately, final String contentId) throws MailException, IOException {
	return new DefaultMailMessageStreamPart(service, name, contentType, inputStream, readImmediately, contentId);
    }

    /*
     * createByteArrayPart methods
     */
    @Override
    public MailMessageByteArrayPart createByteArrayPart(final String name, final String contentType, final byte[] bytes)
	    throws MailException, IOException {
	return new DefaultMailMessageByteArrayPart(service, name, contentType, bytes);
    }

    @Override
    public MailMessageByteArrayPart createByteArrayPart(final String name, final String contentType, final byte[] bytes,
	    final String contentId)
	    throws MailException, IOException {
	return new DefaultMailMessageByteArrayPart(service, name, contentType, bytes, contentId);
    }

    /*
     * createInlineImagePart methods
     */

    /*
     * source File
     */

    @Override
    public MailMessageAttachementPart createInlineImagePart(final String contentType, final File file)
	    throws MailException, IOException {
	try (FileInputStream fis = new FileInputStream(file)) {
	    final byte[] bytes = readBytes(fis);
	    return new DefaultMailMessageAttachementPart(service, contentType, bytes, file.getName(), null,
		    AttachementType.INLINE);
	}
    }

    @Override
    public MailMessageAttachementPart createInlineImagePart(final String contentType, final File file,
	    final String contentId)
	    throws MailException, IOException {
	try (FileInputStream fis = new FileInputStream(file)) {
	    final byte[] bytes = readBytes(fis);
	    return new DefaultMailMessageAttachementPart(service, contentType, bytes, file.getName(), contentId,
		    AttachementType.INLINE);
	}
    }

    /*
     * source InputStream
     */

    @Override
    public MailMessageAttachementPart createInlineImagePart(final String contentType, final InputStream inputStream,
	    final String fileName) throws MailException, IOException {
	final byte[] bytes = readBytes(inputStream);
	return new DefaultMailMessageAttachementPart(service, contentType, bytes, fileName, null,
		AttachementType.INLINE);
    }

    @Override
    public MailMessageAttachementPart createInlineImagePart(final String contentType, final InputStream inputStream,
	    final String fileName, final String contentId) throws MailException, IOException {
	final byte[] bytes = readBytes(inputStream);
	return new DefaultMailMessageAttachementPart(service, contentType, bytes, fileName, contentId,
		AttachementType.INLINE);
    }

    // createAttachement methods

    @Override
    public MailMessageAttachementPart createTextAttachement(final String content, final String contentType,
	    final String fileName) {
	final byte[] bytes = content.getBytes();
	return createBytesAttachement(bytes, contentType, fileName);
    }

    @Override
    public MailMessageAttachementPart createTextAttachement(final String content, final String contentType,
	    final String fileName,
	    final String contentId) {
	final byte[] bytes = content.getBytes();
	return createBytesAttachement(bytes, contentType, fileName, contentId);
    }

    @Override
    public MailMessageAttachementPart createBytesAttachement(final byte[] content, final String contentType,
	    final String fileName) {
	return new DefaultMailMessageAttachementPart(service, contentType, content, fileName, null,
		AttachementType.ATTACHEMENT);
    }

    @Override
    public MailMessageAttachementPart createBytesAttachement(final byte[] content, final String contentType,
	    final String fileName,
	    final String contentId) {
	return new DefaultMailMessageAttachementPart(service, contentType, content, fileName, contentId,
		AttachementType.ATTACHEMENT);
    }

    @Override
    public MailMessageAttachementPart createStreamAttachement(final InputStream content, final String contentType,
	    final String fileName) throws IOException {
	return createStreamAttachement(content, contentType, fileName, null);
    }

    @Override
    public MailMessageAttachementPart createStreamAttachement(final InputStream content, final String contentType,
	    final String fileName,
	    final String contentId) throws IOException {
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int readed = -1;
	final byte[] buff = new byte[256];
	while ((readed = content.read(buff)) != -1)
	    baos.write(buff, 0, readed);
	return createBytesAttachement(baos.toByteArray(), contentType, fileName, contentId);
    }

    // PRIVATE

    private byte[] readBytes(final InputStream inputStream) throws IOException {
	final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	int readed = -1;
	final byte[] buff = new byte[256];
	while ((readed = inputStream.read(buff)) != -1)
	    baos.write(buff, 0, readed);
	return baos.toByteArray();
    }

}
