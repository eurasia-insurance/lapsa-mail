package com.lapsa.mail;

public interface MailAddress {

    /**
     * Use getFriendlyName() instead
     *
     * @return friendly name
     */
    @Deprecated
    String getName();

    /**
     * Returns friendly name of the address owner
     *
     * @return friendly name of the address owner
     */
    String getFriendlyName();

    /**
     * Use getSmtpAddress() instead
     *
     * @return SMTP address
     */
    @Deprecated
    String getEMail();

    /**
     * Returns smtp address in RFC format associated with address
     *
     * @return smtp address
     */
    String getSmtpAddress();

}
