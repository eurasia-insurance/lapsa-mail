package com.lapsa.mailutil;

import java.util.Collection;

public interface MailReceiver extends AutoCloseable {

    boolean hasMessages() throws MailException;

    int countMessages() throws MailException;

    Collection<MailMessage> getAndClear() throws MailException;
}
