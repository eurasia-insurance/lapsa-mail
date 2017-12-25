package tech.lapsa.lapsa.mail.impl;

import static tech.lapsa.lapsa.mail.impl.Checks.*;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.lapsa.mail.MailBuilderException;

abstract class AbstractPart {

    final MimeBodyPart mimePart;

    static enum DispositionType {
	INLINE, ATTACHEMENT;
    }

    AbstractPart(final DefaultMailFactory factory,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	MyObjects.requireNonNull(dispositionType, "Disposition type can not be null");
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
	} catch (final MessagingException e) {
	    throw builderWrapException(e);
	}
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
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
