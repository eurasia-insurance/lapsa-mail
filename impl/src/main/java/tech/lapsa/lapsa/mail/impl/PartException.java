package tech.lapsa.lapsa.mail.impl;

import static tech.lapsa.lapsa.mail.impl.Checks.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.mail.MessagingException;

import tech.lapsa.lapsa.mail.MailBuilderException;

final class PartException extends AbstractPart {
    PartException(final DefaultMailFactory factory,
	    final Exception e,
	    final String fileName,
	    final Charset charset,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(factory, fileName, dispositionType, contentId);
	builderRequireNonNull(e, "Exception can not be null");
	builderRequireNonNull(charset, "Charset can not be null");

	try {
	    mimePart.setText(printedStackTrace(e), charset.name(), "plain");
	} catch (final MessagingException e1) {
	    throw builderWrapException(e1);
	}
    }

    private static String printedStackTrace(final Throwable e) {
	final StringWriter sw = new StringWriter();
	{
	    final PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	}
	return sw.toString();
    }

}
