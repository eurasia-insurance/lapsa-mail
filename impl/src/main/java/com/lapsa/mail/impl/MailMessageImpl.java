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

class MailMessageImpl implements MailMessage {

    private MailAddress from = null;
    private final Map<String, MailAddress> to = new HashMap<String, MailAddress>();
    private final Map<String, MailAddress> cc = new HashMap<String, MailAddress>();
    private final Map<String, MailAddress> bcc = new HashMap<String, MailAddress>();
    private final Set<MailMessagePart> parts = new HashSet<MailMessagePart>();
    private String subject = null;
    private Charset charset = MailMessageBuilder.DEFAULT_CHARSET;

    MailMessageImpl() {
    }

    @Override
    public void addTORecipient(MailAddress recipient) {
	to.put(recipient.getEMail(), recipient);
    }

    @Override
    public void removeTORecipient(MailAddress recipient) {
	to.remove(recipient.getEMail());
    }

    @Override
    public void addPart(MailMessagePart mailMessagePart) {
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
    public void removePart(MailMessagePart mailMessagePart) {
	parts.remove(mailMessagePart);
    }

    @Override
    public void setFrom(MailAddress from) {
	this.from = from;
    }

    @Override
    public MailAddress getFrom() {
	return from;
    }

    @Override
    public void addCCRecipient(MailAddress recipient) {
	cc.put(recipient.getEMail(), recipient);
    }

    @Override
    public MailAddress[] getCCRecipients() {
	return cc.values().toArray(new MailAddress[0]);
    }

    @Override
    public void removeCCRecipient(MailAddress recipient) {
	cc.remove(recipient.getEMail());
    }

    @Override
    public void addBCCRecipient(MailAddress recipient) {
	bcc.put(recipient.getEMail(), recipient);
    }

    @Override
    public MailAddress[] getBCCRecipients() {
	return bcc.values().toArray(new MailAddress[0]);
    }

    @Override
    public void removeBCCRecipient(MailAddress recipient) {
	bcc.remove(recipient.getEMail());
    }

    @Override
    public String getSubject() {
	return subject;
    }

    @Override
    public void setSubject(String subject) {
	this.subject = subject;
    }

    @Override
    public String toString() {
	StringBuffer sb = new StringBuffer();
	if (from != null)
	    sb.append("FROM: " + from + " ");
	if (!to.isEmpty()) {
	    sb.append("TO: ");
	    for (MailAddress ma : to.values())
		sb.append(ma + " ");
	}
	if (!cc.isEmpty()) {
	    sb.append("CC: ");
	    for (MailAddress ma : cc.values())
		sb.append(ma + " ");
	}
	if (!bcc.isEmpty()) {
	    sb.append("BCC: ");
	    for (MailAddress ma : bcc.values())
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
    public void setCharset(Charset charset) {
	this.charset = charset;
    }
}
