package com.lapsa.mailhelper;

import java.io.*;

public interface MailMessageFilePart extends MailMessagePart {
	File getFile();
}
