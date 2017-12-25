package tech.lapsa.lapsa.mail;

public class MailBuilderException extends MailException {
    private static final long serialVersionUID = 3908635929339642030L;

    public MailBuilderException() {
	super();
    }

    public MailBuilderException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public MailBuilderException(final String message) {
	super(message);
    }

    public MailBuilderException(final Throwable cause) {
	super(cause);
    }

}
