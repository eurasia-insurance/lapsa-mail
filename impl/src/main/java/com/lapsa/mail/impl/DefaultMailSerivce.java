package com.lapsa.mail.impl;

import javax.mail.Session;

import com.lapsa.mail.MailService;

public class MailSerivceImpl extends BaseMailService implements MailService {

    private final Session session;

    MailSerivceImpl(final Session session) {
	this.session = session;
    }

    @Override
    protected Session getSession() {
	return session;
    }

}
