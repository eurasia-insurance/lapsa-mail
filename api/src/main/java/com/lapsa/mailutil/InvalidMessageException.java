package com.lapsa.mailutil;

public class InvalidMessageException extends Exception {

	private static final long serialVersionUID = -1975505487071681446L;
	private final MailMessage mailMessage;
	private final String exceptionMessage;

	public InvalidMessageException(String exceptionMessage,
			MailMessage mailMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
		this.mailMessage = mailMessage;
	}

	public MailMessage getMailMessage() {
		return mailMessage;
	}

	@Override
	public String getMessage() {
		return exceptionMessage + " \"" + mailMessage + "\"";
	}
}
