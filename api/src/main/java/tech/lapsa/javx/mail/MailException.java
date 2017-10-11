package tech.lapsa.javx.mail;

public class MailException extends Exception {

    private static final long serialVersionUID = -8073065128525742730L;

    public MailException() {
    }

    public MailException(final String message) {
	super(message);
    }

    public MailException(final Throwable cause) {
	super(cause);
    }

    public MailException(final String message, final Throwable cause) {
	super(message, cause);
    }
}
