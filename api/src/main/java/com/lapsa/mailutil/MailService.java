package com.lapsa.mailutil;

public interface MailService {
	MailSender createSender() throws MailException;

	MailReceiver createReceiver() throws MailException;

	MailMessageBuilder createBuilder() throws MailException;
}
