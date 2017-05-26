package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import java.io.File;

import javax.activation.FileDataSource;

import com.lapsa.mail2.MailBuilderException;

final class PartFile extends AbstractPartDataSource {
    private static final long serialVersionUID = -7858162616103248497L;

    PartFile(final DefaultMailFactory service,
	    final File file,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(service,
		new FileDataSource(builderRequireNonNull(file, "File can not be null")),
		fileName == null ? file.getName() : fileName,
		dispositionType,
		contentId);
    }

}
