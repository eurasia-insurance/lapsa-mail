package com.lapsa.mail;

import java.io.File;

public interface MailMessageFilePart extends MailMessagePart {
    File getFile();
}
