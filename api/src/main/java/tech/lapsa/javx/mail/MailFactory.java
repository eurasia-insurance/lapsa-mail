package tech.lapsa.javx.mail;

public interface MailFactory extends AutoCloseable {
    MailMessageBuilder newMailBuilder() throws MailBuilderException;
}
