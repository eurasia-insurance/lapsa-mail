package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.AbstractPart.DispositionType.*;
import static com.lapsa.mail2.impl.PartText.TextSubtype.*;
import static com.lapsa.mail2.impl.Checks.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.w3c.dom.Document;

import com.lapsa.mail2.MailBuilderException;
import com.lapsa.mail2.MailMessage;
import com.lapsa.mail2.MailMessageBuilder;

final class DefaultMailMessageBuilder implements MailMessageBuilder {

    final DefaultMailFactory service;

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

    DefaultMailMessageBuilder(DefaultMailFactory service,
	    Charset defaultCharset,
	    MailAddress defaultSender,
	    MailAddress defaultRecipient,
	    MailAddress alwaysBlindCopyTo,
	    MailAddress forwardAllMailTo) throws MailBuilderException {
	this.service = service;

	this.defaultCharset = defaultCharset;
	withDefaultCharset();

	this.defaultSender = defaultSender;
	if (defaultSender != null)
	    withDefaultSender();

	this.defaultRecipient = defaultRecipient;
	if (defaultRecipient != null)
	    withDefaultRecipient();

	this.alwaysBlindCopyTo = alwaysBlindCopyTo;
	this.forwardAllMailTo = forwardAllMailTo;
    }

    @Override
    public MailMessageBuilder withAttachement(byte[] binaryData, String mimeType, String fileName)
	    throws MailBuilderException {
	_addPart(new PartBytes(service, binaryData, mimeType, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(byte[] binaryData, String mimeType, String fileName,
	    String contentId)
	    throws MailBuilderException {
	_addPart(new PartBytes(service, binaryData, mimeType, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(Document doc, String fileName)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, сharset, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(Document doc, String fileName, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, charset, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(Document doc, String fileName, Charset charset,
	    String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, charset, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(Document doc, String fileName, String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, сharset, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(Exception e, String fileName)
	    throws MailBuilderException {
	_addPart(new PartException(service, e, fileName, сharset, ATTACHEMENT, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(Exception e, String fileName, String contentId)
	    throws MailBuilderException {
	_addPart(new PartException(service, e, fileName, сharset, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(File file) throws MailBuilderException, IOException {
	_addPart(new PartFile(service, file, null, ATTACHEMENT, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(File file, String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartFile(service, file, null, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(InputStream inputStream, String mimeType, String fileName)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(service, inputStream, mimeType, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(InputStream inputStream, String mimeType, String fileName,
	    String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(service, inputStream, mimeType, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(String textContent, String fileName, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(service, textContent, TEXT, fileName, ATTACHEMENT, charset, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(String textContent, String fileName) throws MailBuilderException {
	_addPart(new PartText(service, textContent, TEXT, fileName, ATTACHEMENT, сharset, null));
	return this;
    }

    @Override
    public MailMessageBuilder withAttachement(String textContent, String fileName, Charset charset,
	    String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(service, textContent, TEXT, fileName, ATTACHEMENT, charset, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAnotherBCCRecipient(String address) throws MailBuilderException {
	bcc = _addRecipient(bcc, address);
	return this;
    }

    @Override
    public MailMessageBuilder withAnotherBCCRecipient(String address, String friendlyName)
	    throws MailBuilderException {
	bcc = _addRecipient(bcc, address, friendlyName);
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withAnotherCCRecipient(String address) throws MailBuilderException {
	cc = _addRecipient(cc, address);
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withAnotherCCRecipient(String address, String friendlyName)
	    throws MailBuilderException {
	cc = _addRecipient(cc, address, friendlyName);
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartDocument(Document doc) throws MailBuilderException {
	_addPart(new PartDocument(service, doc, сharset, null, INLINE, null));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartDocument(Document doc, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, charset, null, INLINE, null));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartDocument(Document doc, Charset charset, String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, charset, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartDocument(Document doc, String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(service, doc, сharset, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartException(Exception e) throws MailBuilderException {
	_addPart(new PartException(service, e, null, сharset, INLINE, null));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartException(Exception e, String contentId)
	    throws MailBuilderException {
	_addPart(new PartException(service, e, null, сharset, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartHtml(String html) throws MailBuilderException {
	_addPart(new PartText(service, html, HTML, null, INLINE, сharset, null));
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withPartHtml(String html, Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(service, html, HTML, null, INLINE, charset, null));
	return this;
    }

    @Override
    public MailMessageBuilder withPartHtml(String html, Charset charset, String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(service, html, HTML, null, INLINE, charset, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withPartHtml(String html, String contentId) throws MailBuilderException {
	_addPart(new PartText(service, html, HTML, null, INLINE, сharset, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withPartInline(byte[] binaryData, String mimeType) throws MailBuilderException {
	_addPart(new PartBytes(service, binaryData, mimeType, null, INLINE, null));
	return this;
    }

    @Override
    public MailMessageBuilder withPartInline(byte[] binaryData, String mimeType, String contentId)
	    throws MailBuilderException {
	_addPart(new PartBytes(service, binaryData, mimeType, null, INLINE, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withPartInline(File file) throws MailBuilderException, IOException {
	_addPart(new PartFile(service, file, null, INLINE, null));
	return this;
    }

    @Override
    public MailMessageBuilder withPartInline(File file, String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartFile(service, file, null, INLINE, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withPartInline(InputStream inputStream, String mimeType)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(service, inputStream, mimeType, null, INLINE, null));
	return this;
    }

    @Override
    public MailMessageBuilder withPartInline(InputStream inputStream, String mimeType, String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(service, inputStream, mimeType, null, INLINE, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withPartText(String text) throws MailBuilderException {
	_addPart(new PartText(service, text, TEXT, null, INLINE, сharset, null));
	return this;
    }

    @Override
    public MailMessageBuilder withPartText(String text, Charset charset) throws MailBuilderException {
	_addPart(new PartText(service, text, TEXT, null, INLINE, charset, null));
	return this;
    }

    @Override
    public MailMessageBuilder withPartText(String text, Charset charset, String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(service, text, TEXT, null, INLINE, charset, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withPartText(String text, String contentId) throws MailBuilderException {
	_addPart(new PartText(service, text, TEXT, null, INLINE, сharset, contentId));
	return this;
    }

    @Override
    public MailMessageBuilder withAnotherTORecipient(String address) throws MailBuilderException {
	to = _addRecipient(to, address);
	return this;
    }

    @Override
    public MailMessageBuilder withAnotherTORecipient(String address, String friendlyName) throws MailBuilderException {
	to = _addRecipient(to, friendlyName, address);
	return this;
    }

    @Override
    public MailMessage build() throws MailBuilderException {
	return new DefaultMailMessage(service, this);
    }

    @Override
    public MailMessageBuilder withBCCRecipient(String address) throws MailBuilderException {
	bcc = _setRecipient(bcc, address);
	return this;
    }

    @Override
    public MailMessageBuilder withBCCRecipient(String address, String friendlyName) throws MailBuilderException {
	bcc = _setRecipient(bcc, address, friendlyName);
	return this;
    }

    @Override
    public MailMessageBuilder withCCRecipient(String address) throws MailBuilderException {
	cc = _setRecipient(cc, address);
	return this;
    }

    @Override
    public MailMessageBuilder withCCRecipient(String address, String friendlyName) throws MailBuilderException {
	cc = _setRecipient(cc, address, friendlyName);
	return this;
    }

    @Override
    public MailMessageBuilder withCharset(Charset charset) throws MailBuilderException {
	this.сharset = builderRequireNonNull(charset, "Charset can not be null");
	return this;
    }

    @Override
    public MailMessageBuilder withSender(String address) throws MailBuilderException {
	this.sender = new MailAddress(address, сharset);
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withSender(String address, String friendlyName)
	    throws MailBuilderException {
	this.sender = new MailAddress(address, friendlyName, сharset);
	return this;
    }

    @Override
    public synchronized MailMessageBuilder withSubject(String subject) {
	this.subject = subject;
	return this;
    }

    @Override
    public MailMessageBuilder withDefaultRecipient() throws MailBuilderException {
	(to = new HashSet<>()).add(builderRequireNonNull(defaultRecipient, "Default recipient is not defined"));
	bcc = null;
	cc = null;
	return this;
    }

    @Override
    public MailMessageBuilder withDefaultSender() throws MailBuilderException {
	sender = builderRequireNonNull(defaultSender, "Default sender is not defined");
	return this;
    }

    @Override
    public MailMessageBuilder withDefaultCharset() throws MailBuilderException {
	this.сharset = builderRequireNonNull(defaultCharset, "Default charset is not defined");
	return this;
    }

    @Override
    public MailMessageBuilder withTORecipient(String address) throws MailBuilderException {
	to = _setRecipient(to, address);
	return this;
    }

    @Override
    public MailMessageBuilder withTORecipient(String address, String friendlyName) throws MailBuilderException {
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
	parts.add(Objects.requireNonNull(part, "Part can not be null"));
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
