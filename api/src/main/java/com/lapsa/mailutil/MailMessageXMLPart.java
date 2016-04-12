package com.lapsa.mailutil;

import java.nio.charset.Charset;

import org.w3c.dom.Document;

public interface MailMessageXMLPart extends MailMessagePart {
    Document getDocument();

    Charset getCharset();
}
