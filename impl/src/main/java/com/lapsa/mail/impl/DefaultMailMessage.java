package com.lapsa.mail.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.lapsa.mail.MailAddress;
import com.lapsa.mail.MailMessage;
import com.lapsa.mail.MailMessageBuilder;
import com.lapsa.mail.MailMessagePart;

class DefaultMailMessage implements MailMessage {

    private MailAddress from = null;
    private final Map<String, MailAddress> to = new HashMap<String, MailAddress>();
    private final Map<String, MailAddress> cc = new HashMap<String, MailAddress>();
    private final Map<String, MailAddress> bcc = new HashMap<String, MailAddress>();
    private final Set<MailMessagePart> parts = new HashSet<MailMessagePart>();
    private String subject = null;
    private Charset charset = MailMessageBuilder.DEFAULT_CHARSET;

    DefaultMailMessage() {
    }

    @Override
    public void addTORecipient(final MailAddress recipient) {
	to.put(recipient.getSmtpAddress(), recipient);
    }

    @Override
    public void removeTORecipient(final MailAddress recipient) {
	to.remove(recipient.getSmtpAddress());
    }

    @Override
    public void addPart(final MailMessagePart mailMessagePart) {
	parts.add(mailMessagePart);
    }

    @Override
    public MailMessagePart[] getParts() {
	return parts.toArray(new MailMessagePart[0]);
    }

    @Override
    public MailAddress[] getTORecipients() {
	return to.values().toArray(new MailAddress[0]);
    }

    @Override
    public void removePart(final MailMessagePart mailMessagePart) {
	parts.remove(mailMessagePart);
    }

    @Override
    public void setFrom(final MailAddress from) {
	this.from = from;
    }

    @Override
    public MailAddress getFrom() {
	return from;
    }

    @Override
    public void addCCRecipient(final MailAddress recipient) {
	cc.put(recipient.getSmtpAddress(), recipient);
    }

    @Override
    public MailAddress[] getCCRecipients() {
	return cc.values().toArray(new MailAddress[0]);
    }

    @Override
    public void removeCCRecipient(final MailAddress recipient) {
	cc.remove(recipient.getSmtpAddress());
    }

    @Override
    public void addBCCRecipient(final MailAddress recipient) {
	bcc.put(recipient.getSmtpAddress(), recipient);
    }

    @Override
    public MailAddress[] getBCCRecipients() {
	return bcc.values().toArray(new MailAddress[0]);
    }

    @Override
    public void removeBCCRecipient(final MailAddress recipient) {
	bcc.remove(recipient.getSmtpAddress());
    }

    @Override
    public String getSubject() {
	return subject;
    }

    @Override
    public void setSubject(final String subject) {
	this.subject = subject;
    }

    @Override
    public String toString() {
	final StringBuffer sb = new StringBuffer();
	if (from != null)
	    sb.append("FROM: " + from + " ");
	if (!to.isEmpty()) {
	    sb.append("TO: ");
	    for (final MailAddress ma : to.values())
		sb.append(ma + " ");
	}
	if (!cc.isEmpty()) {
	    sb.append("CC: ");
	    for (final MailAddress ma : cc.values())
		sb.append(ma + " ");
	}
	if (!bcc.isEmpty()) {
	    sb.append("BCC: ");
	    for (final MailAddress ma : bcc.values())
		sb.append(ma + " ");
	}
	if (subject != null)
	    sb.append("SUBJECT: " + subject + " ");
	return sb.toString();
    }

    @Override
    public Charset getCharset() {
	return charset;
    }

    @Override
    public void setCharset(final Charset charset) {
	this.charset = charset;
    }
}
