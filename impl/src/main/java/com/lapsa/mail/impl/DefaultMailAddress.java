package com.lapsa.mail.impl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.lapsa.mail.MailAddress;

final class DefaultMailAddress implements MailAddress, Serializable {
    private static final long serialVersionUID = -4867263883521799488L;

    final transient DefaultMailService service;

    private transient final String smtpAddress;
    private final String friendlyName;

    DefaultMailAddress(final DefaultMailService service, final String smtpAddress) {
	this(service, smtpAddress, null);
    }

    DefaultMailAddress(final DefaultMailService service, final String smtpAddress, final String friendlyName) {
	this.service = service;
	this.smtpAddress = smtpAddress;
	this.friendlyName = friendlyName;
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(final Object other) {
	return EqualsBuilder.reflectionEquals(this, other, false);
    }

    @Deprecated
    @Override
    public String getName() {
	return getFriendlyName();
    }

    @Override
    public String getFriendlyName() {
	return friendlyName;
    }

    @Deprecated
    @Override
    public String getEMail() {
	return getSmtpAddress();
    }

    @Override
    public String getSmtpAddress() {
	return smtpAddress;
    }

    @Override
    public String toString() {
	return (friendlyName != null ? "\"" + friendlyName + "\" " : "")
		+ (friendlyName != null && smtpAddress != null ? " " : "")
		+ (smtpAddress != null ? "<" + smtpAddress + ">" : "");
    }
}
