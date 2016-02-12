package com.lapsa.mailhelper;

import java.nio.charset.*;

public interface MailMessage {
	void setFrom(MailAddress from);

	MailAddress getFrom();

	void setSubject(String subject);

	String getSubject();

	void addTORecipient(MailAddress recipient);

	void removeTORecipient(MailAddress recipient);

	MailAddress[] getTORecipients();

	void addCCRecipient(MailAddress recipient);

	void removeCCRecipient(MailAddress recipient);

	MailAddress[] getCCRecipients();

	void addBCCRecipient(MailAddress recipient);

	void removeBCCRecipient(MailAddress recipient);

	MailAddress[] getBCCRecipients();

	void addPart(MailMessagePart mailMessagePart);

	void removePart(MailMessagePart mailMessagePart);

	MailMessagePart[] getParts();

	void setCharset(Charset charset);

	Charset getCharset();
}
