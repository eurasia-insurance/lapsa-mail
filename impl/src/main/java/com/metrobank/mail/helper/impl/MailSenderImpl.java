package com.metrobank.mail.helper.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.metrobank.mail.helper.*;

class MailSenderImpl implements MailSender {

    private final Session session;
    private final Logger logger;
    private MailAddress bccAddress;

    MailSenderImpl(Session session) {
	this.session = session;
	logger = Logger.getLogger(this.getClass().getCanonicalName());
    }

    private void _sendOneMessage(Transport transport, MailMessage message) throws MailHelperException,
	    InvalidMessageException {
	try {
	    MimeMessage msg = new MimeMessage(session);

	    MailAddress from = message.getFrom();
	    if (from != null) {
		Address sender = convertAddress(from, message.getCharset());
		msg.setReplyTo(new Address[] { sender });
		msg.setFrom(sender);
	    }

	    String subject = message.getSubject();
	    if (subject != null)
		msg.setSubject(subject, message.getCharset().name());

	    Address[] toRecipients = convertAddressList(message.getTORecipients(), message.getCharset());
	    Address[] ccRecipients = convertAddressList(message.getCCRecipients(), message.getCharset());
	    Address[] bccRecipients = convertAddressList(message.getBCCRecipients(), message.getCharset());

	    if (toRecipients.length + ccRecipients.length + bccRecipients.length == 0)
		throw new InvalidMessageException("Not specified any recipient", message);

	    if (message.getParts() == null || message.getParts().length == 0)
		throw new InvalidMessageException("This is an empty message can not be sent", message);

	    msg.addRecipients(RecipientType.TO, toRecipients);
	    msg.addRecipients(RecipientType.CC, ccRecipients);
	    msg.addRecipients(RecipientType.BCC, bccRecipients);

	    if (bccAddress != null)
		msg.addRecipient(RecipientType.BCC, convertAddress(bccAddress, message.getCharset()));

	    Multipart multipart = new MimeMultipart();
	    MailMessagePart[] parts = message.getParts();

	    for (MailMessagePart part : parts) {
		MultiPartProvider provider = MultiPartProviderFactoryMethod.getProviderFor(part);
		if (provider != null) {
		    BodyPart bodyPart = provider.getBodyPart(part);
		    multipart.addBodyPart(bodyPart);
		}
	    }
	    msg.setContent(multipart);
	    Address[] adrs = msg.getAllRecipients();
	    transport.sendMessage(msg, adrs);
	    logger.log(Level.FINER, "MAIL_SEND_OK " + message);
	} catch (MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_SEND_ERROR " + message, e);
	    throw new MailHelperException(e);
	} catch (UnsupportedEncodingException e) {
	    logger.log(Level.SEVERE, "MAIL_SEND_ERROR " + message, e);
	    throw new MailHelperException(e);
	}
    }

    private static Address convertAddress(MailAddress ma, Charset charset)
	    throws UnsupportedEncodingException {
	return new InternetAddress(ma.getEMail(), ma.getName(), charset.name());
    }

    private static Address[] convertAddressList(MailAddress[] mas, Charset charset)
	    throws UnsupportedEncodingException {
	Collection<Address> result = new ArrayList<Address>(1);
	for (MailAddress ma : mas) {
	    result.add(convertAddress(ma, charset));
	}
	return result.toArray(new Address[0]);
    }

    @Override
    public void send(MailMessage[] messages, MailSendProtocol protocol) throws MailHelperException,
	    InvalidMessageException {
	Transport transport = null;
	try {
	    transport = session.getTransport(protocol.getProtocol());
	    transport.connect();
	    logger.log(Level.FINER, "MAIL_SEND transport connected protocol " + protocol + " OK");
	    for (MailMessage message : messages) {
		_sendOneMessage(transport, message);
	    }
	} catch (NoSuchProviderException e) {
	    throw new MailHelperException(e);
	} catch (MessagingException e) {
	    throw new MailHelperException(e);
	} finally {
	    if (transport != null) {
		try {
		    transport.close();
		} catch (MessagingException e) {
		}
		logger.log(Level.FINER, "MAIL_SEND transport disconnected OK");
	    }
	}
    }

    @Override
    public void send(MailMessage message) throws MailHelperException, InvalidMessageException {
	send(new MailMessage[] { message }, MailSendProtocol.SMTP);
    }

    @Override
    public void send(MailMessage message, MailSendProtocol protocol) throws MailHelperException {
	send(message, MailSendProtocol.SMTP);
    }

    @Override
    public void send(Collection<MailMessage> messages) throws MailHelperException, InvalidMessageException {
	send(messages.toArray(new MailMessage[0]), MailSendProtocol.SMTP);
    }

    @Override
    public void send(Collection<MailMessage> messages, MailSendProtocol protocol) throws MailHelperException,
	    InvalidMessageException {
	send(messages.toArray(new MailMessage[0]), protocol);
    }

    @Override
    public void send(MailMessage[] messages) throws MailHelperException, InvalidMessageException {
	send(messages, MailSendProtocol.SMTP);
    }

    @Override
    public void setAlwaysBlindCopyTo(MailAddress bccAddress) {
	this.bccAddress = bccAddress;
    }
}
