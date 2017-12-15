package com.lapsa.mail.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.lapsa.mail.MailException;
import com.lapsa.mail.MailService;
import com.lapsa.mail.MailServiceFactory;

public final class DefaultMailServiceFactory implements MailServiceFactory {

    @Override
    public MailService createService() throws MailException {
	return createService(new Properties());
    }

    @Override
    public MailService createService(final Properties props) throws MailException {
	return new DefaultMailService(asquireSession(props));
    }

    private static Session asquireSession(final Properties props) {
	if (props.containsKey(MailSessionCustomProperties.MAIL_USER)
		&& props.containsKey(MailSessionCustomProperties.MAIL_PASSWORD)) {
	    final String user = props.getProperty(MailSessionCustomProperties.MAIL_USER);
	    final String password = props.getProperty(MailSessionCustomProperties.MAIL_PASSWORD);
	    final Authenticator a = new Authenticator() {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);
		}
	    };
	    return Session.getDefaultInstance(props, a);
	}

	return Session.getDefaultInstance(props);
    }
}
