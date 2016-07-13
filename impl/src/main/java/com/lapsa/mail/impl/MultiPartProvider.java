package com.lapsa.mail.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

import com.lapsa.mail.MailMessagePart;

interface MultiPartProvider {
    BodyPart getBodyPart(MailMessagePart part) throws MessagingException;
}
