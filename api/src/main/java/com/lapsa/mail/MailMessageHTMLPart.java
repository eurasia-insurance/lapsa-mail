package com.lapsa.mailutil;

import java.nio.charset.Charset;

public interface MailMessageHTMLPart extends MailMessagePart {
    String getHTML();

    Charset getCharset();
}
