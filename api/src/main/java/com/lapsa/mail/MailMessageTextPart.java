package com.lapsa.mail;

import java.nio.charset.Charset;

public interface MailMessageTextPart extends MailMessagePart {
    Charset getCharset();

    String getText();
}
