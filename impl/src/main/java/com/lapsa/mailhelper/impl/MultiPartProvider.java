package com.lapsa.mailhelper.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

import com.lapsa.mailhelper.MailMessagePart;

interface MultiPartProvider {
    BodyPart getBodyPart(MailMessagePart part) throws MessagingException;
}
