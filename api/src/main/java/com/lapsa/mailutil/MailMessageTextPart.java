package com.lapsa.mailutil;

import java.nio.charset.Charset;

public interface MailMessageTextPart extends MailMessagePart {
    Charset getCharset();

    String getText();
}
