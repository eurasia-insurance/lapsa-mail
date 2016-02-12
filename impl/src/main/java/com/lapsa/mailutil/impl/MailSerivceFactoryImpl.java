package com.lapsa.mailutil.impl;

import javax.mail.Session;

public class MailSerivceFactoryImpl extends BaseMailHelperImpl {

    private Session session;

    MailSerivceFactoryImpl(Session session) {
	this.session = session;
    }

    @Override
    protected Session getSession() {
	return session;
    }
}
