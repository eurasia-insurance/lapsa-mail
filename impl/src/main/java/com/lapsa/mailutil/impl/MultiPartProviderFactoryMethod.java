package com.lapsa.mailutil.impl;

import com.lapsa.mailutil.MailMessageByteArrayPart;
import com.lapsa.mailutil.MailMessageFilePart;
import com.lapsa.mailutil.MailMessageHTMLPart;
import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailMessageStreamPart;
import com.lapsa.mailutil.MailMessageTextPart;
import com.lapsa.mailutil.MailMessageURResourcePart;
import com.lapsa.mailutil.MailMessageXMLPart;

abstract class MultiPartProviderFactoryMethod {
    static MultiPartProvider getProviderFor(MailMessagePart part) {
	if (part instanceof MailMessageFilePart)
	    return new MultiPartFileProvider();
	if (part instanceof MailMessageURResourcePart)
	    return new MultiPartURLResourceProvider();
	if (part instanceof MailMessageTextPart)
	    return new MultiPartTextProvider();
	if (part instanceof MailMessageHTMLPart)
	    return new MultiPartHTMLProvider();
	if (part instanceof MailMessageXMLPart)
	    return new MultiPartXMLProvider();
	if (part instanceof MailMessageStreamPart)
	    return new MultiPartStreamProvider();
	if (part instanceof MailMessageByteArrayPart)
	    return new MultiPartByteArrayProvider();
	return null;
    }
}
