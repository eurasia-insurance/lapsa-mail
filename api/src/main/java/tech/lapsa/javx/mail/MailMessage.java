package com.lapsa.mail2;

public interface MailMessage {
    void send() throws MailSendException;
    void sendAgain() throws MailSendException;
    boolean isSent();
}
