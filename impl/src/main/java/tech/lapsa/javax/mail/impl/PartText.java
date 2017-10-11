package tech.lapsa.javax.mail.impl;

import static tech.lapsa.javax.mail.impl.Checks.*;

import java.nio.charset.Charset;

import javax.mail.MessagingException;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.javax.mail.MailBuilderException;

final class PartText extends AbstractPart {
    static enum TextSubtype {
	TEXT("plain"), HTML("html");
	final String subtype;

	TextSubtype(String subtype) {
	    this.subtype = subtype;
	}
    }

    PartText(final DefaultMailFactory factory,
	    final String textContent,
	    final TextSubtype textSubtype,
	    final String fileName,
	    final DispositionType dispositionType,
	    final Charset charset,
	    final String contentId) throws MailBuilderException {
	super(factory, fileName, dispositionType, contentId);
	try {
	    mimePart.setText(builderRequireNonNull(textContent, "Content can not be null"),
		    builderRequireNonNull(charset, "Charset can not be null").name(),
		    MyObjects.requireNonNull(textSubtype).subtype);
	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}
    }

}
