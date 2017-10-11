package tech.lapsa.javax.mail.impl;

import static tech.lapsa.javax.mail.impl.Checks.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.mail.MessagingException;

import org.w3c.dom.Document;

import com.lapsa.utils.DocumentUtils;

import tech.lapsa.javax.mail.MailBuilderException;

final class PartDocument extends AbstractPart {
    PartDocument(final DefaultMailFactory factory,
	    final Document doc,
	    final Charset charset,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(factory, fileName, dispositionType, contentId);

	try {
	    String content = DocumentUtils.getAsString(
		    builderRequireNonNull(doc, "XML DOM object can not be null"),
		    builderRequireNonNull(charset, "Charset can not be null").name());
	    mimePart.setText(content, charset.name(), "xml");
	} catch (MessagingException | UnsupportedEncodingException e) {
	    throw builderWrapException(e);
	}

    }
}
