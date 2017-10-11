package tech.lapsa.javx.mail.impl;

import static tech.lapsa.javx.mail.impl.Checks.*;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.util.ByteArrayDataSource;

import tech.lapsa.javx.mail.MailBuilderException;

final class PartInputStream extends AbstractPartDataSource {
    PartInputStream(final DefaultMailFactory factory,
	    final InputStream inputStream,
	    final String mimeType,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException, IOException {
	super(factory,
		new ByteArrayDataSource(
			builderRequireNonNull(inputStream, "Input stream can not be null"),
			builderRequireNonNull(mimeType, "Mime type can not be null")),
		fileName,
		dispositionType,
		contentId);
    }

}
