package com.lapsa.mailutil;

import java.util.Collection;

public interface MailSender extends AutoCloseable {
    void setAlwaysBlindCopyTo(MailAddress bccAddress);

    MailAddress getDefaultSender();

    void send(MailMessage message) throws MailException, InvalidMessageException;

    void send(Collection<MailMessage> messages) throws MailException, InvalidMessageException;

    void send(MailMessage[] messages) throws MailException, InvalidMessageException;

    @Override
    void close() throws MailException;
}
