package com.lapsa.mailutil;

import java.io.IOException;
import java.io.InputStream;

public interface MailMessageStreamPart extends MailMessagePart {
    InputStream getInputStream() throws IOException;

    String getContentType();

    String getName();
}
