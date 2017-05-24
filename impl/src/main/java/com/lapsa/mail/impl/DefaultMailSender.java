package com.lapsa.mail.impl;

import static com.lapsa.mail.impl.MailSessionCustomProperties.*;

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

import com.lapsa.mail.InvalidMessageException;
import com.lapsa.mail.MailAddress;
import com.lapsa.mail.MailException;
import com.lapsa.mail.MailMessage;
import com.lapsa.mail.MailMessagePart;
import com.lapsa.mail.MailSender;
import com.lapsa.mail.MailService;

final class DefaultMailSender implements MailSender {

    private final Session session;
    private final MailService service;
    private final Logger logger;

    private MailAddress bccAddress;
    private boolean alwaysBlindCopy = false;

    private MailAddress forceMailAddress;
    private boolean alwaysForceMail = false;

    private Transport transport;

    DefaultMailSender(final MailService service, final Session session) {
	this.session = session;
	this.service = service;
	logger = Logger.getLogger(this.getClass().getCanonicalName());
	if (session.getProperty(MAIL_BCC) != null)
	    try {
		bccAddress = service.createBuilder().createAddress(session.getProperty(MAIL_BCC));
		alwaysBlindCopy = true;
	    } catch (final MailException ignored) {
	    }
	if (session.getProperty(MAIL_FORCETO) != null)
	    try {
		forceMailAddress = service.createBuilder().createAddress(session.getProperty(MAIL_FORCETO));
		alwaysForceMail = true;
	    } catch (final MailException ignored) {
	    }
    }

    private JobForTransport buildJobForTransport(final MailMessage message)
	    throws MailException, InvalidMessageException {
	try {
	    final MimeMessage msg = new MimeMessage(session);

	    final MailAddress from = message.getFrom();
	    if (from != null) {
		final Address sender = convertAddress(from, message.getCharset());
		msg.setReplyTo(new Address[] { sender });
		msg.setFrom(sender);
	    }

	    final String subject = message.getSubject();
	    if (subject != null)
		msg.setSubject(subject, message.getCharset().name());

	    if (alwaysForceMail) {
		// if mail must be forced
		if (forceMailAddress == null)
		    // but force mail address is empty
		    throw new MailException("Always force mail is set to 'true' but forceMailAddress is empty");
		msg.addRecipient(RecipientType.TO, convertAddress(forceMailAddress, message.getCharset()));
	    } else {
		// other cases (default)
		final Address[] toRecipients = convertAddressList(message.getTORecipients(), message.getCharset());
		final Address[] ccRecipients = convertAddressList(message.getCCRecipients(), message.getCharset());
		final Address[] bccRecipients = convertAddressList(message.getBCCRecipients(), message.getCharset());

		if (toRecipients.length + ccRecipients.length + bccRecipients.length == 0)
		    throw new InvalidMessageException("Not specified any recipient", message);

		msg.addRecipients(RecipientType.TO, toRecipients);
		msg.addRecipients(RecipientType.CC, ccRecipients);
		msg.addRecipients(RecipientType.BCC, bccRecipients);
	    }

	    if (alwaysBlindCopy && bccAddress != null)
		msg.addRecipient(RecipientType.BCC, convertAddress(bccAddress, message.getCharset()));

	    if (message.getParts() == null || message.getParts().length == 0)
		msg.setContent("", "text/plain");
	    else {
		final Multipart multipart = new MimeMultipart();
		final MailMessagePart[] parts = message.getParts();

		for (final MailMessagePart part : parts) {
		    if (part instanceof MultiPartProvider) {
			MultiPartProvider provider = (MultiPartProvider) part;
			final BodyPart bodyPart = provider.getBodyPart();
			multipart.addBodyPart(bodyPart);
		    }
		}
		msg.setContent(multipart);
	    }

	    final Address[] adrs = msg.getAllRecipients();
	    return new JobForTransport(msg, adrs);

	} catch (final MessagingException e) {
	    logger.log(Level.SEVERE, "MAIL_SEND_ERROR " + message, e);
	    throw new MailException(e);
	} catch (final UnsupportedEncodingException e) {
	    logger.log(Level.SEVERE, "MAIL_SEND_ERROR " + message, e);
	    throw new MailException(e);
	}
    }

    private static Address convertAddress(final MailAddress ma, final Charset charset)
	    throws UnsupportedEncodingException {
	return new InternetAddress(ma.getSmtpAddress(), ma.getFriendlyName(), charset.name());
    }

    private static Address[] convertAddressList(final MailAddress[] mas, final Charset charset)
	    throws UnsupportedEncodingException {
	final Collection<Address> result = new ArrayList<Address>(1);
	for (final MailAddress ma : mas)
	    result.add(convertAddress(ma, charset));
	return result.toArray(new Address[0]);
    }

    @Override
    public void send(final MailMessage[] messages) throws MailException, InvalidMessageException {
	final JobForTransport[] jobs = new JobForTransport[messages.length];
	for (int i = 0; i < messages.length; i++)
	    jobs[i] = buildJobForTransport(messages[i]);
	try {
	    autoConnect();
	    for (final JobForTransport jfs : jobs) {
		transport.sendMessage(jfs.msg, jfs.adrs);
		logger.fine("MAIL_SEND_OK " + jfs.msg.getMessageID());
	    }
	} catch (final NoSuchProviderException e) {
	    throw new MailException(e);
	} catch (final MessagingException e) {
	    throw new MailException(e);
	}
    }

    @Override
    public void send(final MailMessage message) throws MailException, InvalidMessageException {
	send(new MailMessage[] { message });
    }

    @Override
    public void send(final Collection<MailMessage> messages) throws MailException, InvalidMessageException {
	send(messages.toArray(new MailMessage[0]));
    }

    @Override
    public MailAddress getAlwaysBlindCopyTo() {
	return bccAddress;
    }

    @Override
    public void setAlwaysBlindCopyTo(final MailAddress bccAddress) {
	this.bccAddress = bccAddress;
    }

    @Override
    public boolean getAlwaysBlindCopy() {
	return alwaysBlindCopy;
    }

    @Override
    public void setAlwaysBlindCopy(final boolean alwaysBlindCopy) {
	this.alwaysBlindCopy = alwaysBlindCopy;
    }

    @Override
    public MailAddress getForceMailAddress() {
	return forceMailAddress;
    }

    @Override
    public void setForceMailAddress(final MailAddress forceMailAddress) {
	this.forceMailAddress = forceMailAddress;
    }

    @Override
    public boolean isAlwaysForceMail() {
	return alwaysForceMail;
    }

    @Override
    public void setAlwaysForceMail(final boolean alwaysForceMail) {
	this.alwaysForceMail = alwaysForceMail;
    }

    private void autoConnect() throws MessagingException {
	if (transport == null)
	    transport = session.getTransport();
	if (!transport.isConnected()) {
	    transport.connect(session.getProperty(MAIL_USER), session.getProperty(MAIL_PASSWORD));
	    logger.fine("MAIL_SEND transport connected OK");
	}
    }

    @Override
    public void close() throws MailException {
	if (transport != null && transport.isConnected())
	    try {
		transport.close();
		logger.fine("MAIL_SEND transport disconnected OK");
	    } catch (final MessagingException e) {
		logger.log(Level.SEVERE, "MAIL_SEND_ERROR", e);
		throw new MailException(e);
	    }
    }

    @Deprecated
    @Override
    public MailAddress getDefaultSender() {
	return service.getDefaultSender();
    }

    @Deprecated
    @Override
    public MailAddress getDefaultRecipient() {
	return service.getDefaultRecipient();
    }

    @Deprecated
    @Override
    public MailAddress getDefaultBCCRecipient() {
	return service.getDefaultBCCRecipient();
    }
}

class JobForTransport {

    MimeMessage msg;
    Address[] adrs;

    public JobForTransport(final MimeMessage msg, final Address[] adrs) {
	this.msg = msg;
	this.adrs = adrs;
    }

}
