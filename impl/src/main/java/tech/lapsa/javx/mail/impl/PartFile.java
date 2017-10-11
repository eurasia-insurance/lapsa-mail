package tech.lapsa.javx.mail.impl;

import static tech.lapsa.javx.mail.impl.Checks.*;

import java.io.File;

import javax.activation.FileDataSource;

import tech.lapsa.javx.mail.MailBuilderException;

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
