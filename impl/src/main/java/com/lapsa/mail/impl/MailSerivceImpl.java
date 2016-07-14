package com.lapsa.mail.impl;

import javax.mail.Session;

import com.lapsa.mail.MailService;

public class MailSerivceFactoryImpl extends BaseMailService implements MailService {

    private final Session session;

    MailSerivceFactoryImpl(final Session session) {
	this.session = session;
    }

    @Override
    protected Session getSession() {
	return session;
    }

}
