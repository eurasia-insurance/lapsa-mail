package com.metrobank.mail.helper.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

import com.metrobank.mail.helper.MailMessagePart;

interface MultiPartProvider {
    BodyPart getBodyPart(MailMessagePart part) throws MessagingException;
}
