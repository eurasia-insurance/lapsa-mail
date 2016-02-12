package com.lapsa.mailhelper.impl;

import com.lapsa.mailhelper.MailMessageFilePart;
import com.lapsa.mailhelper.MailMessageHTMLPart;
import com.lapsa.mailhelper.MailMessagePart;
import com.lapsa.mailhelper.MailMessageStreamPart;
import com.lapsa.mailhelper.MailMessageTextPart;
import com.lapsa.mailhelper.MailMessageXMLPart;

abstract class MultiPartProviderFactoryMethod {
    static MultiPartProvider getProviderFor(MailMessagePart part) {
	if (part instanceof MailMessageFilePart)
	    return new MultiPartFileProvider();
	if (part instanceof MailMessageTextPart)
	    return new MultiPartTextProvider();
	if (part instanceof MailMessageHTMLPart)
	    return new MultiPartHTMLProvider();
	if (part instanceof MailMessageXMLPart)
	    return new MultiPartXMLProvider();
	if (part instanceof MailMessageStreamPart)
	    return new MultiPartStreamProvider();
	return null;
    }
}
