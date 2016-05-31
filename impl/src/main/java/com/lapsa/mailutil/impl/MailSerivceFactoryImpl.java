package com.lapsa.mailutil.impl;

import javax.mail.Session;

import com.lapsa.mailutil.MailService;

public class MailSerivceFactoryImpl extends BaseMailHelperImpl implements MailService {

    private Session session;

    MailSerivceFactoryImpl(Session session) {
	this.session = session;
    }

    @Override
    protected Session getSession() {
	return session;
    }

}
