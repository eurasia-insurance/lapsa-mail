package tech.lapsa.javax.mail;

public class MailBuilderException extends MailException {
    private static final long serialVersionUID = 3908635929339642030L;

    public MailBuilderException() {
	super();
    }

    public MailBuilderException(String message, Throwable cause) {
	super(message, cause);
    }

    public MailBuilderException(String message) {
	super(message);
    }

    public MailBuilderException(Throwable cause) {
	super(cause);
    }

}
