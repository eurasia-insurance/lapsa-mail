package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.DefaultMailMessageBuilder.*;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.util.ByteArrayDataSource;

import com.lapsa.mail2.MailBuilderException;

final class PartInputStream extends APartDataSource {
    private static final long serialVersionUID = -7858162616103248497L;

    PartInputStream(final DefaultMailFactory service,
	    final InputStream inputStream,
	    final String mimeType,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException, IOException {
	super(service,
		new ByteArrayDataSource(
			builderRequireNonNull(inputStream, "Input stream can not be null"),
			builderRequireNonNull(mimeType, "Mime type can not be null")),
		fileName,
		dispositionType,
		contentId);
    }

}
