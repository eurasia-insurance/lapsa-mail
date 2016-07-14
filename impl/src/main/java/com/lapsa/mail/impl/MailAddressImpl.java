package com.lapsa.mail.impl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.lapsa.mail.MailAddress;

class MailAddressImpl implements MailAddress, Serializable {
    private static final long serialVersionUID = -4867263883521799488L;
    private static final int PRIME = 3;
    private static final int MULTIPLIER = 3;

    private final String friendlyName;
    private final String smtpAddress;

    MailAddressImpl(String smtpAddress, String friendlyName) {
	this.smtpAddress = smtpAddress;
	this.friendlyName = friendlyName;
    }

    MailAddressImpl(String smtpAddress) {
	this.smtpAddress = smtpAddress;
	this.friendlyName = null;
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder(PRIME, MULTIPLIER)
		.append(smtpAddress)
		.toHashCode();
    }

    @Override
    public boolean equals(Object other) {
	if (other == null || other.getClass() != getClass())
	    return false;
	if (other == this)
	    return true;
	MailAddressImpl that = (MailAddressImpl) other;
	return new EqualsBuilder()
		.append(smtpAddress, that.smtpAddress)
		.isEquals();
    };

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
