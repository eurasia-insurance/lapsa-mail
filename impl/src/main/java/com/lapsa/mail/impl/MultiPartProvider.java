package com.lapsa.mail.impl;

import javax.mail.BodyPart;
import javax.mail.MessagingException;

@FunctionalInterface
interface MultiPartProvider {
    BodyPart getBodyPart() throws MessagingException;
}
