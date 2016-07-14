package com.lapsa.mail.impl;

import com.lapsa.mail.MailMessageAttachementPart;
import com.lapsa.mail.MailMessageByteArrayPart;
import com.lapsa.mail.MailMessageFilePart;
import com.lapsa.mail.MailMessageHTMLPart;
import com.lapsa.mail.MailMessagePart;
import com.lapsa.mail.MailMessageStreamPart;
import com.lapsa.mail.MailMessageTextPart;
import com.lapsa.mail.MailMessageXMLPart;

abstract class MultiPartProviderFactoryMethod {
    static MultiPartProvider getProviderFor(final MailMessagePart part) {
	if (part instanceof MailMessageFilePart)
	    return new MailMessageFilePartProvider();
	if (part instanceof MailMessageTextPart)
	    return new MailMessageTextPartProvider();
	if (part instanceof MailMessageHTMLPart)
	    return new MailMessageHTMLPartProvider();
	if (part instanceof MailMessageXMLPart)
	    return new MailMessageXMLPartProvider();
	if (part instanceof MailMessageStreamPart)
	    return new MailMessageStreamPartProvider();
	if (part instanceof MailMessageByteArrayPart)
	    return new MailMessageByteArrayProvider();
	if (part instanceof MailMessageAttachementPart)
	    return new MailMessageAttachementPartProvider();
	return null;
    }
}
