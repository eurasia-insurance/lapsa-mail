package com.lapsa.mail;

public interface MailMessageByteArrayPart extends MailMessagePart {
    byte[] getBytes();

    String getContentType();

    String getName();

}
