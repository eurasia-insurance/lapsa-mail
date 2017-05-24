package com.lapsa.mail.impl;

import javax.mail.Session;

public final class PublicMailService extends DefaultMailService {
    public PublicMailService(final Session session) {
	super(session);
    }
}
