package com.lapsa.mailutil;

public class MailException extends Exception {

    private static final long serialVersionUID = -8073065128525742730L;

    public MailException() {
    }

    public MailException(String message) {
	super(message);
    }

    public MailException(Throwable cause) {
	super(cause);
    }

    public MailException(String message, Throwable cause) {
	super(message, cause);
    }
}
