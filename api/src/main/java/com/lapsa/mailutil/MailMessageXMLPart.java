package com.lapsa.mailutil;

import java.nio.charset.*;

import org.w3c.dom.*;

public interface MailMessageXMLPart extends MailMessagePart {
	Document getDocument();

	Charset getCharset();
}
