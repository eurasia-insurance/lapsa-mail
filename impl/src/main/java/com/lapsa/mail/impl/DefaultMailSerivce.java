package com.lapsa.mail.impl;

import javax.mail.Session;

import com.lapsa.mail.MailService;

final class DefaultMailSerivce extends BaseMailService implements MailService {

    private final Session session;

    DefaultMailSerivce(final Session session) {
	this.session = session;
    }

    @Override
    protected Session getSession() {
	return session;
    }

}
