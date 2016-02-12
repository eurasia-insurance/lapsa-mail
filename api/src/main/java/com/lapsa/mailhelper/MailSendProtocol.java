package com.lapsa.mailhelper;

public enum MailSendProtocol {
	SMTP("smtp");

	private final String protocol;

	private MailSendProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getProtocol() {
		return protocol;
	}

}
