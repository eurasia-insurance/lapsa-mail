package com.lapsa.mail2.impl;

import static com.lapsa.mail2.impl.Checks.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;

import com.lapsa.mail2.MailBuilderException;

import tech.lapsa.java.commons.function.MyObjects;

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
	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}
    }
}
