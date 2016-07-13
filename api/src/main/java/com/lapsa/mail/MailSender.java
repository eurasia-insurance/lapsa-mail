package com.lapsa.mailutil;

import java.util.Collection;

public interface MailSender extends AutoCloseable {

    MailAddress getAlwaysBlindCopyTo();

    void setAlwaysBlindCopyTo(MailAddress bccAddress);

    boolean getAlwaysBlindCopy();

    void setAlwaysBlindCopy(boolean alwaysBlindCopy);

    MailAddress getForceMailAddress();

    void setForceMailAddress(MailAddress forceMailAddress);

    boolean isAlwaysForceMail();

    void setAlwaysForceMail(boolean alwaysForceMail);

    @Deprecated
    MailAddress getDefaultSender();

    @Deprecated
    MailAddress getDefaultRecipient();

    @Deprecated
    MailAddress getDefaultBCCRecipient();

    void send(MailMessage message) throws MailException, InvalidMessageException;

    void send(Collection<MailMessage> messages) throws MailException, InvalidMessageException;

    void send(MailMessage[] messages) throws MailException, InvalidMessageException;

    @Override
    void close() throws MailException;
}
