package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail2.MailBuilderException;

final class PartBytes extends AbstractPartDataSource {
    PartBytes(final DefaultMailFactory factory,
	    final byte[] bytes,
	    final String mimeType,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(factory,
		new ByteArrayDataSource(
			builderRequireNonNull(bytes, "Input stream can not be null"),
			builderRequireNonNull(mimeType, "Mime type can not be null")),
		fileName,
		dispositionType,
		contentId);
    }

}
