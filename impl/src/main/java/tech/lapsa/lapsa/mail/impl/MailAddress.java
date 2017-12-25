package tech.lapsa.javax.mail.impl;

import static tech.lapsa.javax.mail.impl.Checks.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.javax.mail.MailBuilderException;

final class MailAddress {
    final InternetAddress internetAddress;

    MailAddress(final String address, final Charset charset) throws MailBuilderException {
	this(address, null, charset);
    }

    MailAddress(final String address, final String friendlyName,
	    final Charset charset)
	    throws MailBuilderException {
	builderRequireNonNull(address, "Address can not be null");
	MyObjects.requireNonNull(charset, "Charset can not be null");
	try {
	    internetAddress = new InternetAddress(address, friendlyName, charset.name());
	} catch (final UnsupportedEncodingException e) {
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

}
