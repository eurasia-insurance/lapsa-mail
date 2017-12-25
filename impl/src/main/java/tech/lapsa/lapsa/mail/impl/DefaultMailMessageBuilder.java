package tech.lapsa.lapsa.mail.impl;

import static tech.lapsa.lapsa.mail.impl.AbstractPart.DispositionType.*;
import static tech.lapsa.lapsa.mail.impl.Checks.*;
import static tech.lapsa.lapsa.mail.impl.PartText.TextSubtype.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.lapsa.mail.MailBuilderException;
import tech.lapsa.lapsa.mail.MailMessageBuilder;

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

	defaultCharset = factory.defaultCharset;
	withDefaultCharset();

	defaultSender = factory.defaultSender;
	if (defaultSender != null)
	    withDefaultSender();

	defaultRecipient = factory.defaultRecipient;
	if (defaultRecipient != null)
	    withDefaultRecipient();

	alwaysBlindCopyTo = factory.alwaysBlindCopyTo;
	forwardAllMailTo = factory.forwardAllMailTo;
    }

    @Override
    public DefaultMailMessage build() throws MailBuilderException {
	return new DefaultMailMessage(factory, this);
    }

    @Override
    public DefaultMailMessageBuilder withAnotherBCCRecipient(final String address) throws MailBuilderException {
	bcc = _addRecipient(bcc, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAnotherBCCRecipient(final String address, final String friendlyName)
	    throws MailBuilderException {
	bcc = _addRecipient(bcc, address, friendlyName);
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withAnotherCCRecipient(final String address)
	    throws MailBuilderException {
	cc = _addRecipient(cc, address);
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withAnotherCCRecipient(final String address,
	    final String friendlyName)
	    throws MailBuilderException {
	cc = _addRecipient(cc, address, friendlyName);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAnotherTORecipient(final String address) throws MailBuilderException {
	to = _addRecipient(to, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAnotherTORecipient(final String address, final String friendlyName)
	    throws MailBuilderException {
	to = _addRecipient(to, friendlyName, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withAttachement(final Exception e, final String fileName, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartException(factory, e, fileName, сharset, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBCCRecipient(final String address) throws MailBuilderException {
	bcc = _setRecipient(bcc, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBCCRecipient(final String address, final String friendlyName)
	    throws MailBuilderException {
	bcc = _setRecipient(bcc, address, friendlyName);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBinaryPart(final byte[] binaryData, final String mimeType)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, null, INLINE, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBinaryPart(final byte[] binaryData, final String mimeType,
	    final String contentId)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, null, INLINE, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBytesAttached(final byte[] binaryData, final String mimeType,
	    final String fileName)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withBytesAttached(final byte[] binaryData, final String mimeType,
	    final String fileName,
	    final String contentId)
	    throws MailBuilderException {
	_addPart(new PartBytes(factory, binaryData, mimeType, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withCCRecipient(final String address) throws MailBuilderException {
	cc = _setRecipient(cc, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withCCRecipient(final String address, final String friendlyName)
	    throws MailBuilderException {
	cc = _setRecipient(cc, address, friendlyName);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withCharset(final Charset charset) throws MailBuilderException {
	сharset = builderRequireNonNull(charset, "Charset can not be null");
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDefaultCharset() throws MailBuilderException {
	сharset = builderRequireNonNull(defaultCharset, "Default charset is not defined");
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
    public DefaultMailMessageBuilder withDocumentAttached(final Document doc, final String fileName)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(final Document doc, final String fileName,
	    final Charset charset)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(final Document doc, final String fileName,
	    final Charset charset,
	    final String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withDocumentAttached(final Document doc, final String fileName,
	    final String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(final Document doc) throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, null, INLINE, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(final Document doc, final Charset charset)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, null, INLINE, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(final Document doc, final Charset charset,
	    final String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, charset, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withDocumentPart(final Document doc, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartDocument(factory, doc, сharset, null, INLINE, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withExceptionAttached(final Exception e, final String fileName)
	    throws MailBuilderException {
	_addPart(new PartException(factory, e, fileName, сharset, ATTACHEMENT, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withExceptionPart(final Exception e) throws MailBuilderException {
	_addPart(new PartException(factory, e, null, сharset, INLINE, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withExceptionPart(final Exception e, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartException(factory, e, null, сharset, INLINE, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFileAttached(final File file) throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFileAttached(final File file, final String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFilePart(final File file) throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, INLINE, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withFilePart(final File file, final String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartFile(factory, file, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withHtmlPart(final String html) throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, сharset, null));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withHtmlPart(final String html, final Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, charset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withHtmlPart(final String html, final Charset charset, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, charset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withHtmlPart(final String html, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, html, HTML, null, INLINE, сharset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withSender(final String address) throws MailBuilderException {
	sender = new MailAddress(address, сharset);
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withSender(final String address, final String friendlyName)
	    throws MailBuilderException {
	sender = new MailAddress(address, friendlyName, сharset);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamAttached(final InputStream inputStream, final String mimeType,
	    final String fileName)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, fileName, ATTACHEMENT, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamAttached(final InputStream inputStream, final String mimeType,
	    final String fileName,
	    final String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, fileName, ATTACHEMENT, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamPart(final InputStream inputStream, final String mimeType)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, null, INLINE, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withStreamPart(final InputStream inputStream, final String mimeType,
	    final String contentId)
	    throws MailBuilderException, IOException {
	_addPart(new PartInputStream(factory, inputStream, mimeType, null, INLINE, contentId));
	return this;
    }

    @Override
    public synchronized DefaultMailMessageBuilder withSubject(final String subject) {
	this.subject = subject;
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextAttached(final String textContent, final String fileName)
	    throws MailBuilderException {
	_addPart(new PartText(factory, textContent, TEXT, fileName, ATTACHEMENT, сharset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextAttached(final String textContent, final String fileName,
	    final Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(factory, textContent, TEXT, fileName, ATTACHEMENT, charset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextAttached(final String textContent, final String fileName,
	    final Charset charset,
	    final String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, textContent, TEXT, fileName, ATTACHEMENT, charset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(final String text) throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, сharset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(final String text, final Charset charset)
	    throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, charset, null));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(final String text, final Charset charset, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, charset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTextPart(final String text, final String contentId)
	    throws MailBuilderException {
	_addPart(new PartText(factory, text, TEXT, null, INLINE, сharset, contentId));
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTORecipient(final String address) throws MailBuilderException {
	to = _setRecipient(to, address);
	return this;
    }

    @Override
    public DefaultMailMessageBuilder withTORecipient(final String address, final String friendlyName)
	    throws MailBuilderException {
	to = _setRecipient(to, address, friendlyName);
	return this;
    }

    private synchronized List<AbstractPart> _addPart(final AbstractPart part) {
	return _updatePartsList(part, false);
    }

    private synchronized Set<MailAddress> _addRecipient(final Set<MailAddress> set, final String address)
	    throws MailBuilderException {
	return _addRecipientAddr(set, new MailAddress(address, сharset));
    }

    private synchronized Set<MailAddress> _addRecipient(final Set<MailAddress> set, final String address,
	    final String friendlyName)
	    throws MailBuilderException {
	return _addRecipientAddr(set, new MailAddress(address, friendlyName, сharset));
    }

    private synchronized Set<MailAddress> _addRecipientAddr(final Set<MailAddress> set, final MailAddress addr)
	    throws MailBuilderException {
	return _updateRecipientAddressList(set, false, addr);
    }

    private synchronized Set<MailAddress> _setRecipient(final Set<MailAddress> set, final String address)
	    throws MailBuilderException {
	return _setRecipientAddr(set, new MailAddress(address, сharset));
    }

    private synchronized Set<MailAddress> _setRecipient(final Set<MailAddress> set, final String address,
	    final String friendlyName)
	    throws MailBuilderException {
	return _setRecipientAddr(set, new MailAddress(address, friendlyName, сharset));
    }

    private synchronized Set<MailAddress> _setRecipientAddr(final Set<MailAddress> set, final MailAddress addr)
	    throws MailBuilderException {
	return _updateRecipientAddressList(set, true, addr);
    }

    private synchronized List<AbstractPart> _updatePartsList(final AbstractPart part, final boolean forcedInit) {
	if (parts == null || forcedInit)
	    parts = new ArrayList<>();
	parts.add(MyObjects.requireNonNull(part, "Part can not be null"));
	return parts;
    }

    private synchronized Set<MailAddress> _updateRecipientAddressList(Set<MailAddress> set, final boolean forcedInit,
	    final MailAddress... addrs)
	    throws MailBuilderException {
	if (set == null || forcedInit)
	    set = new HashSet<>();
	for (final MailAddress addr : addrs) {
	    builderRequireNotIn(addr, set, "Address is already set: " + addr);
	    set.add(addr);
	}
	return set;
    }
}
