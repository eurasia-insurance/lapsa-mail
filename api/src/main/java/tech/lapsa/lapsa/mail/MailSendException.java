package tech.lapsa.lapsa.mail;

public class MailSendException extends MailException {
    private static final long serialVersionUID = -131508577849959266L;

    public MailSendException() {
	super();
    }

    public MailSendException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public MailSendException(final String message) {
	super(message);
    }

    public MailSendException(final Throwable cause) {
	super(cause);
    }

}
