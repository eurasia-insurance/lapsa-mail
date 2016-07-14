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
	    return new MailMessageFilePartMultiPartProvider();
	if (part instanceof MailMessageTextPart)
	    return new MailMessageTextPartMultiPartProvider();
	if (part instanceof MailMessageHTMLPart)
	    return new MailMessageHTMLPartMultiPartProvider();
	if (part instanceof MailMessageXMLPart)
	    return new MailMessageXMLPartMultiPartProvider();
	if (part instanceof MailMessageStreamPart)
	    return new MailMessageStreamPartMultiPartProvider();
	if (part instanceof MailMessageByteArrayPart)
	    return new MailMessageByteArrayMultiPartProvider();
	if (part instanceof MailMessageAttachementPart)
	    return new MailMessageAttachementMultiPartProvider();
	return null;
    }
}
