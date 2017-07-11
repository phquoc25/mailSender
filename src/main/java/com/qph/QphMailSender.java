package com.qph;

import javax.mail.MessagingException;

/**
 * Created by Quoc PHAN on 11/07/2017.
 */
public interface QphMailSender {
    void sendMail() throws MessagingException;

    /**
     * Send email to multiple receiver
     * @param receivers a receiver list separated by comma
     * @throws MessagingException
     */
    void sendMail(String receivers) throws MessagingException;
}
