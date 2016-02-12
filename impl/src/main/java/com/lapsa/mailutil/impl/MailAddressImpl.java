package com.lapsa.mailutil.impl;

import com.lapsa.mailutil.MailAddress;

class MailAddressImpl implements MailAddress {

    private String name;
    private String eMail;

    MailAddressImpl(String eMail, String name) {
	this.eMail = eMail;
	this.name = name;
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
