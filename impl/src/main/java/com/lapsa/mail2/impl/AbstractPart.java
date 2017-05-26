package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import java.io.Serializable;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.lapsa.mail.MailMessagePart;
import com.lapsa.mail2.MailBuilderException;

abstract class AbstractPart implements MailMessagePart, Serializable {
    private static final long serialVersionUID = -2790508720253272737L;

    final transient DefaultMailFactory service;

    final MimeBodyPart mimePart;

    static enum DispositionType {
	INLINE, ATTACHEMENT;
    }

    AbstractPart(final DefaultMailFactory service,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	this.service = Objects.requireNonNull(service);

	Objects.requireNonNull(dispositionType, "Disposition type can not be null");
	if (dispositionType == DispositionType.ATTACHEMENT)
	    builderRequireNonNull(fileName, "File name of the attachement can not be null");

	mimePart = new MimeBodyPart();

	try {
	    if (contentId != null)
		mimePart.setContentID(String.format("<%1$s>", contentId));
	    if (fileName != null)
		mimePart.setFileName(fileName);
	    switch (dispositionType) {
	    case INLINE:
		mimePart.setDisposition(Part.INLINE);
		break;
	    case ATTACHEMENT:
		mimePart.setDisposition(Part.ATTACHMENT);
		break;
	    }
	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(final Object other) {
	return EqualsBuilder.reflectionEquals(this, other, false);
    }
}
