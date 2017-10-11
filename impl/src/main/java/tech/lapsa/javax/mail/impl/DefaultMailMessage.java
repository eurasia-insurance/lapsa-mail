package tech.lapsa.javax.mail.impl;

import javax.mail.Message.RecipientType;

import static tech.lapsa.javax.mail.impl.Checks.*;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import tech.lapsa.javax.mail.MailBuilderException;
import tech.lapsa.javax.mail.MailMessage;
import tech.lapsa.javax.mail.MailSendException;

final class DefaultMailMessage implements MailMessage {

    final transient DefaultMailFactory factory;
    transient boolean sent = false;

    final MimeMessage msg;

    DefaultMailMessage(DefaultMailFactory factory, DefaultMailMessageBuilder template)
	    throws MailBuilderException {
	this.factory = factory;
	this.msg = new MimeMessage(factory.session);

	// from
	try {
	    if (template.sender != null)
		msg.setFrom(template.sender.internetAddress);
	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}

	// recipients
	recipients: try {
	    int totalRecipientsCount = (template.to != null ? template.to.size() : 0) +
		    (template.cc != null ? template.cc.size() : 0) +
		    (template.bcc != null ? template.bcc.size() : 0);

	    if (totalRecipientsCount == 0)
		throw new MailBuilderException("There is no any recipient defined");

	    if (template.alwaysBlindCopyTo != null)
		msg.addRecipient(RecipientType.BCC, template.alwaysBlindCopyTo.internetAddress);

	    if (template.forwardAllMailTo != null) {
		msg.addRecipient(RecipientType.TO, template.forwardAllMailTo.internetAddress);
		break recipients; // no needs to add additional recipients,
				  // cause all must be forwards
	    }

	    if (template.to != null)
		for (MailAddress address : template.to)
		    msg.addRecipient(RecipientType.TO, address.internetAddress);

	    if (template.cc != null)
		for (MailAddress address : template.cc)
		    msg.addRecipient(RecipientType.CC, address.internetAddress);

	    if (template.bcc != null)
		for (MailAddress address : template.bcc)
		    msg.addRecipient(RecipientType.BCC, address.internetAddress);

	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}

	// subject
	try {
	    msg.setSubject(template.subject, template.defaultCharset.name());
	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}

	// body

	body: try {
	    if (template.parts == null || template.parts.size() == 0) {
		msg.setContent("", "text/plain"); // empty messages are allowed
		break body;
	    }

	    final Multipart multipart = new MimeMultipart();
	    for (AbstractPart part : template.parts) {
		multipart.addBodyPart(part.mimePart);
	    }
	    msg.setContent(multipart);

	} catch (MessagingException e) {
	    throw builderWrapException(e);
	}
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(final Object other) {
	return EqualsBuilder.reflectionEquals(this, other, false);
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
    }

    @Override
    public void send() throws MailSendException {
	if (sent)
	    throw new MailSendException("Message already sent successfuly");
	sendAgain();
    }

    @Override
    public synchronized void sendAgain() throws MailSendException {
	try {
	    factory.getTransportConnected().sendMessage(msg, msg.getAllRecipients());
	    sent = true;
	} catch (MessagingException e) {
	    throw senderWrapException(e);
	}
    }

    @Override
    public boolean isSent() {
	return sent;
    }
}
