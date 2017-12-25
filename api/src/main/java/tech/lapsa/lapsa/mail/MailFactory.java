package tech.lapsa.lapsa.mail;

public interface MailFactory extends AutoCloseable {
    MailMessageBuilder newMailBuilder() throws MailBuilderException;
}
