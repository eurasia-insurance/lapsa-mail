package com.metrobank.mail.helper;

import java.io.File;

public interface MailMessageFilePart extends MailMessagePart {
    File getFile();
}
