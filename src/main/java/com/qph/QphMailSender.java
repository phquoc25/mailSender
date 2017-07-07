package com.qph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by quoc on 21/06/2017.
 */
@Service
public class QphMailSender {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_PART = "<date>";
    private static final String TODAY;
    static{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        TODAY = simpleDateFormat.format(new Date());
    }
    @Autowired
    @Qualifier(value = "otherMailSender")
    private JavaMailSenderImpl mailSender;

    @Autowired
    private QphMailSenderProperties senderProperties;

    public void sendMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(senderProperties.getSender());
        mimeMessageHelper.setTo(senderProperties.getReceivers());
        mimeMessageHelper.setSubject(senderProperties.getSubject());
        mimeMessageHelper.setText(senderProperties.getBody(), true);
        mimeMessageHelper.addAttachment(senderProperties.getAttachmentFileName().replace(DATE_PART, TODAY),
                new FileSystemResource(senderProperties.getAttachmentFilePath().replace(DATE_PART, TODAY)));
        this.mailSender.send(mimeMessage);
    }
}
