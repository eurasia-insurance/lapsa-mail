package com.lapsa.mailutil.impl;

import com.lapsa.mailutil.MailAddress;

class MailAddressImpl implements MailAddress {

    private final String name;
    private final String eMail;

    MailAddressImpl(String eMail, String name) {
	this.eMail = eMail;
	this.name = name;
    }

    MailAddressImpl(String eMail) {
	this.eMail = eMail;
	this.name = null;
    }

    public String getEMail() {
	return eMail;
    }

    public String getName() {
	return name;
    }

    public String toString() {
	return (name != null ? "\"" + name + "\" " : "") + (name != null && eMail != null ? " " : "")
		+ (eMail != null ? "<" + eMail + ">" : "");
    }
}
