package com.lapsa.mailhelper.impl;

import javax.mail.*;

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
