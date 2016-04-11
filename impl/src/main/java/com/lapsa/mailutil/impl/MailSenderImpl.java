package com.lapsa.mailutil.impl;

import static com.lapsa.mailutil.impl.MailPropertyNames.*;

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

import com.lapsa.mailutil.InvalidMessageException;
import com.lapsa.mailutil.MailAddress;
import com.lapsa.mailutil.MailException;
import com.lapsa.mailutil.MailMessage;
import com.lapsa.mailutil.MailMessagePart;
import com.lapsa.mailutil.MailSendProtocol;
import com.lapsa.mailutil.MailSender;

class MailSenderImpl implements MailSender {

    private final Session session;
    private final Logger logger;
    private MailAddress bccAddress;

    private Transport transport;

    MailSenderImpl(Session session) {
	this.session = session;
	logger = Logger.getLogger(this.getClass().getCanonicalName());
    }

    private JobForTransport buildJobForTransport(MailMessage message) throws MailException, InvalidMessageException {
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
	    return new JobForTransport(msg, adrs);

	} catch (MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_SEND_ERROR " + message, e);
	    throw new MailException(e);
	} catch (UnsupportedEncodingException e) {
	    logger.log(Level.SEVERE, "MAIL_SEND_ERROR " + message, e);
	    throw new MailException(e);
	}
    }

    private static Address convertAddress(MailAddress ma, Charset charset) throws UnsupportedEncodingException {
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
    public void send(MailMessage[] messages, MailSendProtocol protocol) throws MailException, InvalidMessageException {
	JobForTransport[] jobs = new JobForTransport[messages.length];
	for (int i = 0; i < messages.length; i++) {
	    jobs[i] = buildJobForTransport(messages[i]);
	}
	try {
	    autoConnect();
	    for (JobForTransport jfs : jobs) {
		transport.sendMessage(jfs.msg, jfs.adrs);
		logger.log(Level.FINE, "MAIL_SEND_OK " + jfs.msg.getMessageID());
	    }
	} catch (NoSuchProviderException e) {
	    throw new MailException(e);
	} catch (MessagingException e) {
	    throw new MailException(e);
	}
    }

    @Override
    public void send(MailMessage message) throws MailException, InvalidMessageException {
	send(new MailMessage[] { message }, MailSendProtocol.SMTP);
    }

    @Override
    public void send(MailMessage message, MailSendProtocol protocol) throws MailException {
	send(message, MailSendProtocol.SMTP);
    }

    @Override
    public void send(Collection<MailMessage> messages) throws MailException, InvalidMessageException {
	send(messages.toArray(new MailMessage[0]), MailSendProtocol.SMTP);
    }

    @Override
    public void send(Collection<MailMessage> messages, MailSendProtocol protocol)
	    throws MailException, InvalidMessageException {
	send(messages.toArray(new MailMessage[0]), protocol);
    }

    @Override
    public void send(MailMessage[] messages) throws MailException, InvalidMessageException {
	send(messages, MailSendProtocol.SMTP);
    }

    @Override
    public void setAlwaysBlindCopyTo(MailAddress bccAddress) {
	this.bccAddress = bccAddress;
    }

    @Override
    public MailAddress getDefaultSender() {
	String from = session.getProperty(MAIL_FROM);
	if (from != null) {
	    return new MailAddressImpl(from, "");
	}
	return null;
    }

    private void autoConnect() throws MessagingException {
	if (transport == null)
	    transport = session.getTransport();
	if (!transport.isConnected()) {
	    transport.connect(session.getProperty(MAIL_USER), session.getProperty(MAIL_PASSWORD));
	    logger.log(Level.FINE, "MAIL_SEND transport connected OK");
	}
    }

    @Override
    public void close() {
	if (transport != null && transport.isConnected()) {
	    try {
		transport.close();
	    } catch (MessagingException e) {
		logger.log(Level.SEVERE, "MAIL_SEND_ERROR", e);
	    }
	    logger.log(Level.FINE, "MAIL_SEND transport disconnected OK");
	}
    }
}

class JobForTransport {

    MimeMessage msg;
    Address[] adrs;

    public JobForTransport(MimeMessage msg, Address[] adrs) {
	this.msg = msg;
	this.adrs = adrs;
    }

}
