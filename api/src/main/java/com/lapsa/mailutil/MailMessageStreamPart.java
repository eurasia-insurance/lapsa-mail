package com.lapsa.mailutil;

import java.io.*;

public interface MailMessageStreamPart extends MailMessagePart {
	InputStream getInputStream() throws IOException;

	String getContentType();

	String getName();
}
