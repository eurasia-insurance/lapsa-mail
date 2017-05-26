package com.lapsa.mail2;

public interface MailFactory extends AutoCloseable {
    MailMessageBuilder newMailBuilder() throws MailBuilderException;
}
