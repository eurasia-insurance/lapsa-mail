package com.lapsa.mailhelper;

import java.nio.charset.*;

import org.w3c.dom.*;

public interface MailMessageXMLPart extends MailMessagePart {
	Document getDocument();

	Charset getCharset();
}
