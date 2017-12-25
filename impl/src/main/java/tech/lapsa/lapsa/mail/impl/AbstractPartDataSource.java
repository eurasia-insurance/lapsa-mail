package tech.lapsa.lapsa.mail.impl;

import static tech.lapsa.lapsa.mail.impl.Checks.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.lapsa.mail.MailBuilderException;

abstract class AbstractPartDataSource extends AbstractPart {
    AbstractPartDataSource(final DefaultMailFactory factory,
	    final DataSource dataSource,
	    final String fileName,
	    final DispositionType dispositionType,
	    final String contentId) throws MailBuilderException {
	super(factory, fileName, dispositionType, contentId);
	try {
	    final DataHandler dh = new DataHandler(
		    MyObjects.requireNonNull(dataSource, "Datasource data can not be null"));
	    mimePart.setDataHandler(dh);
	} catch (final MessagingException e) {
	    throw builderWrapException(e);
	}
    }
}
