package com.metrobank.mail.helper;

import java.io.*;

public interface MailMessageFilePart extends MailMessagePart {
	File getFile();
}
