package tech.lapsa.javax.mail;

public interface MailFactory extends AutoCloseable {
    MailMessageBuilder newMailBuilder() throws MailBuilderException;
}
