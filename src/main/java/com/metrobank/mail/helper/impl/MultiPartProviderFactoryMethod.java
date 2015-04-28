package com.metrobank.mail.helper.impl;

import com.metrobank.mail.helper.MailMessageFilePart;
import com.metrobank.mail.helper.MailMessageHTMLPart;
import com.metrobank.mail.helper.MailMessagePart;
import com.metrobank.mail.helper.MailMessageStreamPart;
import com.metrobank.mail.helper.MailMessageTextPart;
import com.metrobank.mail.helper.MailMessageXMLPart;

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
