package com.lapsa.mailutil;

import java.io.File;

public interface MailMessageFilePart extends MailMessagePart {
    File getFile();
}
