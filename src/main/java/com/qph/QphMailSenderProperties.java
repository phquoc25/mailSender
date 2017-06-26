package com.qph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by quoc on 25/06/2017.
 */
@Component
public class QphMailSenderProperties {

    @Value("${host}")
    private String host;
    @Value("${port}")
    private String port;
    @Value("${username}")
    private String userName;
    @Value("${password}")
    private String password;
    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;
    @Value("${mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${sender}")
    private String sender;
    @Value("${receivers}")
    private String receivers;
    @Value("${subject}")
    private String subject;
    @Value("${body}")
    private String body;
    @Value("${attachment.file.name}")
    private String attachmentFileName;
    @Value("${attachment.file.path}")
    private String attachmentFilePath;

    @Value("${gmail.username}")
    private String gmailUserName;
    @Value("${gmail.password}")
    private String gmailPassword;

    public String getHost() {
        return host;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public String getSender() {
        return sender;
    }

    public String getReceivers() {
        return receivers;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getPort() {
        return port;
    }

    public String getMailSmtpStarttlsEnable() {
        return mailSmtpStarttlsEnable;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public String getAttachmentFilePath() {
        return attachmentFilePath;
    }

    public String getGmailUserName()
    {
        return gmailUserName;
    }

    public String getGmailPassword()
    {
        return gmailPassword;
    }
}
