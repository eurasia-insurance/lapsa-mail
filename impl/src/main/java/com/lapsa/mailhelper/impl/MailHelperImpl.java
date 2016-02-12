package com.lapsa.mailhelper.impl;

import javax.mail.*;

public class MailHelperImpl extends BaseMailHelperImpl {

	private Session session;

	MailHelperImpl(Session session) {
		this.session = session;
	}

	@Override
	protected Session getSession() {
		return session;
	}
}
