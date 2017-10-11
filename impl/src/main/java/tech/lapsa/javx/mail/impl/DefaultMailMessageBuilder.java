package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.AbstractPart.DispositionType.*;
import static com.lapsa.mail2.impl.Checks.*;
import static com.lapsa.mail2.impl.PartText.TextSubtype.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;

import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailMessageBuilder;

import tech.lapsa.java.commons.function.MyObjects;

final class DefaultMailMessageBuilder implements MailMessageBuilder {

    final DefaultMailFactory factory;

    final MailAddress defaultSender;
    final MailAddress defaultRecipient;
    final Charset defaultCharset;

    MailAddress sender;
    Set<MailAddress> to;
    Set<MailAddress> cc;
    Set<MailAddress> bcc;
    List<AbstractPart> parts;
    String subject;
    Charset сharset;

    MailAddress alwaysBlindCopyTo;
    MailAddress forwardAllMailTo;

    DefaultMailMessageBuilder(final DefaultMailFactory factory) throws MailBuilderException {
	this.factory = factory;

	this.defaultCharset = factory.defaultCharset;
	withDefaultCharset();

	this.defaultSender = factory.defaultSender;
	if (defaultSender != null)
	    withDefaultSender();

	this.defaultRecipient = factory.defaultRecipient;
	if (defaultRecipient != null)
	    withDefaultRecipient();

	this.alwaysBlindCopyTo = factory.alwaysBlindCopyTo;
	this.forwardAllMailTo = factory.forwardAllMailTo;
    }

    @Override
    public DefaultMailMessage build() throws MailBuilderException {
	return new DefaultMailMessage(factory, this);
    }

    @Override
    public DefaultMailMessageBuilder withAnotherBCCRecipient(String address) throws MailBuilderException {
	bcc = _addRecipient(bcc, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAnotherBCCRecipient(String address, String friendlyName)
	    throws MailBuilderException {
	bcc = _addRecipient(bcc, address, friendlyName);
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withAnotherCCRecipient(String address) throws MailBuilderException {
	cc = _addRecipient(cc, address);
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withAnotherCCRecipient(String address, String friendlyName)
	    throws MailBuilderException {
	cc = _addRecipient(cc, address, friendlyName);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAnotherTORecipient(String address) throws MailBuilderException {
	to = _addRecipient(to, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAnotherTORecipient(String address, String friendlyName)
	    throws MailBuilderException {
	to = _addRecipient(to, friendlyName, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAttachement(Exception e, String fileName, String contentId)
	    throws MailBuilderException {
	_addPart(new PartException(factory, e, fileName, сharset, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBCCRecipient(String address) throws MailBuilderException {
	bcc = _setRecipient(bcc, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBCCRecipient(String address, String friendlyName) throws MailBuilderException {
	bcc = _setRecipient(bcc, address, friendlyName);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBinaryPart(byte[] binaryData, String mimeType) throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, null, INLINE, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBinaryPart(byte[] binaryData, String mimeType, String contentId)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, null, INLINE, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBytesAttached(byte[] binaryData, String mimeType, String fileName)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBytesAttached(byte[] binaryData, String mimeType, String fileName,
	    String contentId)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withCCRecipient(String address) throws MailBuilderException {
	cc = _setRecipient(cc, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withCCRecipient(String address, String friendlyName) throws MailBuilderException {
	cc = _setRecipient(cc, address, friendlyName);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withCharset(Charset charset) throws MailBuilderException {
	this.сharset = builderRequireNonNull(charset, "Charset can not be null");
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDefaultCharset() throws MailBuilderException {
	this.сharset = builderRequireNonNull(defaultCharset, "Default charset is not defined");
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDefaultRecipient() throws MailBuilderException {
	(to = new HashSet<>()).add(builderRequireNonNull(defaultRecipient, "Default recipient is not defined"));
	bcc = null;
	cc = null;
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDefaultSender() throws MailBuilderException {
	sender = builderRequireNonNull(defaultSender, "Default sender is not defined");
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(Document doc, String fileName)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(Document doc, String fileName, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(Document doc, String fileName, Charset charset,
	    String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(Document doc, String fileName, String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(Document doc) throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, null, INLINE, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(Document doc, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, null, INLINE, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(Document doc, Charset charset, String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(Document doc, String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, null, INLINE, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withExceptionAttached(Exception e, String fileName)
	    throws MailBuilderException {
	_addPart(new PartException(factory, e, fileName, сharset, ATTACHEMENT, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withExceptionPart(Exception e) throws MailBuilderException {
	_addPart(new PartException(factory, e, null, сharset, INLINE, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withExceptionPart(Exception e, String contentId)
	    throws MailBuilderException {
	_addPart(new PartException(factory, e, null, сharset, INLINE, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFileAttached(File file) throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFileAttached(File file, String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFilePart(File file) throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, INLINE, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFilePart(File file, String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withHtmlPart(String html) throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, сharset, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withHtmlPart(String html, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, charset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withHtmlPart(String html, Charset charset, String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, charset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withHtmlPart(String html, String contentId) throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, сharset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withSender(String address) throws MailBuilderException {
	this.sender = new MailAddress(address, сharset);
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withSender(String address, String friendlyName)
	    throws MailBuilderException {
	this.sender = new MailAddress(address, friendlyName, сharset);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamAttached(InputStream inputStream, String mimeType, String fileName)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamAttached(InputStream inputStream, String mimeType, String fileName,
	    String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamPart(InputStream inputStream, String mimeType)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, null, INLINE, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamPart(InputStream inputStream, String mimeType, String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withSubject(String subject) {
	this.subject = subject;
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextAttached(String textContent, String fileName) throws MailBuilderException {
	_addPart(new PartText(factory, textContent, TEXT, fileName, ATTACHEMENT, сharset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextAttached(String textContent, String fileName, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(factory, textContent, TEXT, fileName, ATTACHEMENT, charset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextAttached(String textContent, String fileName, Charset charset,
	    String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, textContent, TEXT, fileName, ATTACHEMENT, charset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(String text) throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, сharset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(String text, Charset charset) throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, charset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(String text, Charset charset, String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, charset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(String text, String contentId) throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, сharset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTORecipient(String address) throws MailBuilderException {
	to = _setRecipient(to, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTORecipient(String address, String friendlyName) throws MailBuilderException {
	to = _setRecipient(to, address, friendlyName);
	return this;
    }

    private synchronized List<AbstractPart> _addPart(AbstractPart part) {
	return _updatePartsList(part, false);
    }

    private synchronized Set<MailAddress> _addRecipient(Set<MailAddress> set, String address)
	    throws MailBuilderException {
	return _addRecipientAddr(set, new MailAddress(address, сharset));
    }

    private synchronized Set<MailAddress> _addRecipient(Set<MailAddress> set, String address, String friendlyName)
	    throws MailBuilderException {
	return _addRecipientAddr(set, new MailAddress(address, friendlyName, сharset));
    }

    private synchronized Set<MailAddress> _addRecipientAddr(Set<MailAddress> set, MailAddress addr)
	    throws MailBuilderException {
	return _updateRecipientAddressList(set, false, addr);
    }

    private synchronized Set<MailAddress> _setRecipient(Set<MailAddress> set, String address)
	    throws MailBuilderException {
	return _setRecipientAddr(set, new MailAddress(address, сharset));
    }

    private synchronized Set<MailAddress> _setRecipient(Set<MailAddress> set, String address, String friendlyName)
	    throws MailBuilderException {
	return _setRecipientAddr(set, new MailAddress(address, friendlyName, сharset));
    }

    private synchronized Set<MailAddress> _setRecipientAddr(Set<MailAddress> set, MailAddress addr)
	    throws MailBuilderException {
	return _updateRecipientAddressList(set, true, addr);
    }

    private synchronized List<AbstractPart> _updatePartsList(AbstractPart part, boolean forcedInit) {
	if (parts == null || forcedInit)
	    parts = new ArrayList<>();
	parts.add(MyObjects.requireNonNull(part, "Part can not be null"));
	return parts;
    }

    private synchronized Set<MailAddress> _updateRecipientAddressList(Set<MailAddress> set, boolean forcedInit,
	    MailAddress... addrs)
	    throws MailBuilderException {
	if (set == null || forcedInit)
	    set = new HashSet<>();
	for (MailAddress addr : addrs) {
	    builderRequireNotIn(addr, set, "Address is already set: " + addr);
	    set.add(addr);
	}
	return set;
    }
}
