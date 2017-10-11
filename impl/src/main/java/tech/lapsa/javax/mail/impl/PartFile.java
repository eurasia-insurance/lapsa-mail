package tech.lapsa.javax.mail.impl;

import static tech.lapsa.javax.mail.impl.Checks.*;

import java.io.File;

import javax.activation.FileDataSource;

import tech.lapsa.javax.mail.MailBuilderException;

final class PartFile extends AbstractPartDataSource {
    PartFile(final DefaultMailFactory factory,
	    final File file,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(factory,
		new FileDataSource(builderRequireNonNull(file, "File can not be null")),
		fileName == null ? file.getName() : fileName,
		dispositionType,
		contentId);
    }

}
