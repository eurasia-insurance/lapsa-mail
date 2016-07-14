package com.lapsa.mail;

import java.io.IOException;
import java.io.InputStream;

public interface MailMessageAttachementPart extends MailMessagePart {

    byte[] getContents();

    InputStream getNewContentsInputStream() throws IOException;

    String getContentType();

    String getAttachementFileName();

    AttachementType getType();
}
