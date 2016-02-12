package com.lapsa.mailutil;

import java.nio.charset.*;

public interface MailMessageHTMLPart extends MailMessagePart {
	String getHTML();

	Charset getCharset();
}
