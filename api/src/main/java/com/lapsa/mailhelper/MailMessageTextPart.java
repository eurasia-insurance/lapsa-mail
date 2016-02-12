package com.lapsa.mailhelper;

import java.nio.charset.*;

public interface MailMessageTextPart extends MailMessagePart {
	Charset getCharset();

	String getText();
}
