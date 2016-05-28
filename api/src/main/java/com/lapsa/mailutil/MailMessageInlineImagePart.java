package com.lapsa.mailutil;

import java.io.IOException;
import java.io.InputStream;

public interface MailMessageInlineImagePart extends MailMessagePart {

    String getContentType();

    InputStream getNewInputStream() throws IOException;

    byte[] getByteArray();

    String getFileName();
}
