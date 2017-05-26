package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.mail.MessagingException;

import com.lapsa.mail.MailMessageTextPart;
import com.lapsa.mail2.MailBuilderException;

final class PartException extends AbstractPart implements MailMessageTextPart {
    private static final long serialVersionUID = -7858162616103248497L;

    PartException(final DefaultMailFactory service,
	    final Exception e,
	    final String fileName,
	    final Charset charset,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(service, fileName, dispositionType, contentId);
	builderRequireNonNull(e, "Exception can not be null");
	builderRequireNonNull(charset, "Charset can not be null");
	final StringWriter sw = new StringWriter();
	final PrintWriter pw = new PrintWriter(sw);
	e.printStackTrace(pw);
	try {
	    mimePart.setText(pw.toString(), charset.name(), "plain");
	} catch (MessagingException e1) {
	    throw builderWrapException(e1);
	}
    }

}
