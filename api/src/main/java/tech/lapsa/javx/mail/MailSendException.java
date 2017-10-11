package tech.lapsa.javx.mail;

public class MailSendException extends MailException {
    private static final long serialVersionUID = -131508577849959266L;

    public MailSendException() {
	super();
    }

    public MailSendException(String message, Throwable cause) {
	super(message, cause);
    }

    public MailSendException(String message) {
	super(message);
    }

    public MailSendException(Throwable cause) {
	super(cause);
    }

}
