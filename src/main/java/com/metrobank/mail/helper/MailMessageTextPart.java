package com.metrobank.mail.helper;

import java.nio.charset.Charset;

public interface MailMessageTextPart extends MailMessagePart {
    Charset getCharset();

    String getText();
}
