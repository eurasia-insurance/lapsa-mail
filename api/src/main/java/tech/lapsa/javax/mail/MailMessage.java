package tech.lapsa.javax.mail;

public interface MailMessage {
    void send() throws MailSendException;
    void sendAgain() throws MailSendException;
    boolean isSent();
}
