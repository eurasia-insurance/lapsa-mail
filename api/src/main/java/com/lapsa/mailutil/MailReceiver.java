package com.lapsa.mailutil;

import java.util.Collection;

public interface MailReceiver {

    boolean hasNewMessages() throws MailException;

    int countNewMessages() throws MailException;

    Collection<MailMessage> getAndClear() throws MailException;
}
