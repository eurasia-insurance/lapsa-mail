package com.metrobank.mail.helper;

import java.io.*;

public interface MailMessageStreamPart extends MailMessagePart {
	InputStream getInputStream() throws IOException;

	String getContentType();

	String getName();
}
