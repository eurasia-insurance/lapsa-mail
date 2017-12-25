package tech.lapsa.lapsa.mail;

public interface MailMessage {
    void send() throws MailSendException;

    void sendAgain() throws MailSendException;

    boolean isSent();
}
