package com.lapsa.mailutil;

public interface MailReceiver extends AutoCloseable {

    boolean hasMessages() throws MailException;

    int countMessages() throws MailException;

    void сlearMessages() throws MailException;

    @Override
    void close() throws MailException;
}
