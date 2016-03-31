package com.lapsa.mailutil;

import java.util.*;

public interface MailSender {
	void setAlwaysBlindCopyTo(MailAddress bccAddress);

	MailAddress getDefaultSender();

	void send(MailMessage message) throws MailException,
			InvalidMessageException;

	void send(MailMessage message, MailSendProtocol protocol)
			throws MailException, InvalidMessageException;

	void send(Collection<MailMessage> messages) throws MailException,
			InvalidMessageException;

	void send(Collection<MailMessage> messages, MailSendProtocol protocol)
			throws MailException, InvalidMessageException;

	void send(MailMessage[] messages) throws MailException,
			InvalidMessageException;

	void send(MailMessage[] messages, MailSendProtocol protocol)
			throws MailException, InvalidMessageException;
}
