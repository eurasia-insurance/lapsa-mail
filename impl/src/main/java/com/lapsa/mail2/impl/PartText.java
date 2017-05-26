package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import java.nio.charset.Charset;
import java.util.Objects;

import javax.mail.MessagingException;

import com.lapsa.mail2.MailBuilderException;

final class PartText extends APart {
    private static final long serialVersionUID = 1307985859507746378L;

    static enum TextSubtype {
	TEXT("plain"), HTML("html");
	final String subtype;

	TextSubtype(String subtype) {
	    this.subtype = subtype;
	}
    }

    PartText(final DefaultMailFactory service,
	    final String textContent,
	    final TextSubtype textSubtype,
	    final String fileName,
	    final DispositionType dispositionType,
	    final Charset charset,
	    final String contentId) throws MailBuilderException {
	super(service, fileName, dispositionType, contentId);
	try {
	    mimePart.setText(builderRequireNonNull(textContent, "Content can not be null"),
		    builderRequireNonNull(charset, "Charset can not be null").name(),
		    Objects.requireNonNull(textSubtype).subtype);
	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}
    }

}
