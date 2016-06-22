package com.lapsa.mailutil.impl;

import com.lapsa.mailutil.MailMessageAttachementPart;
import com.lapsa.mailutil.MailMessageByteArrayPart;
import com.lapsa.mailutil.MailMessageFilePart;
import com.lapsa.mailutil.MailMessageHTMLPart;
import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageStreamPart;
import com.lapsa.mailutil.MailMessageTextPart;
import com.lapsa.mailutil.MailMessageXMLPart;

abstract class MultiPartProviderFactoryMethod {
    static MultiPartProvider getProviderFor(MailMessagePart part) {
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
