package com.metrobank.mail.helper;

import java.nio.charset.*;

public interface MailMessageTextPart extends MailMessagePart {
	Charset getCharset();

	String getText();
}
