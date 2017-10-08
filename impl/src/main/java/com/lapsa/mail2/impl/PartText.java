package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import java.nio.charset.Charset;

import javax.mail.MessagingException;

import com.lapsa.commons.function.MyObjects;
import com.lapsa.mail2.MailBuilderException;

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
