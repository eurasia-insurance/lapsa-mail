package com.lapsa.mail;

public interface MailService {
    MailAddress getDefaultSender();

    MailAddress getDefaultRecipient();

    MailAddress getDefaultBCCRecipient();

    MailSender createSender() throws MailException;

    MailReceiver createReceiver() throws MailException;

    MailMessageBuilder createBuilder() throws MailException;
}
