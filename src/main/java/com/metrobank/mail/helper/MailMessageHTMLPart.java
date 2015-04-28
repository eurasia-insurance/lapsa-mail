package com.metrobank.mail.helper;

import java.nio.charset.Charset;

public interface MailMessageHTMLPart extends MailMessagePart {
    String getHTML();

    Charset getCharset();
}
