package com.lapsa.mailhelper;

public class MailHelperException extends Exception {

	private static final long serialVersionUID = -8073065128525742730L;

	public MailHelperException() {
	}

	public MailHelperException(String message) {
		super(message);
	}

	public MailHelperException(Throwable cause) {
		super(cause);
	}

	public MailHelperException(String message, Throwable cause) {
		super(message, cause);
	}
}
