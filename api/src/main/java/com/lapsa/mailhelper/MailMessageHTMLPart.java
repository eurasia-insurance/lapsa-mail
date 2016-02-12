package com.lapsa.mailhelper;

import java.nio.charset.*;

public interface MailMessageHTMLPart extends MailMessagePart {
	String getHTML();

	Charset getCharset();
}
