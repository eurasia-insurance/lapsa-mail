package com.metrobank.mail.helper;

import java.nio.charset.*;

public interface MailMessageHTMLPart extends MailMessagePart {
	String getHTML();

	Charset getCharset();
}
