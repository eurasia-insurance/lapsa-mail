package com.lapsa.mail;

import java.nio.charset.Charset;

public interface MailMessageHTMLPart extends MailMessagePart {
    String getHTML();

    Charset getCharset();
}
