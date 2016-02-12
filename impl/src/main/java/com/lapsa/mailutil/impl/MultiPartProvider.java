package com.lapsa.mailutil.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

import com.lapsa.mailutil.MailMessagePart;

interface MultiPartProvider {
    BodyPart getBodyPart(MailMessagePart part) throws MessagingException;
}
