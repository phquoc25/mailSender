package com.qph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by quoc on 21/06/2017.
 */
@Service
public class QphMailSenderImpl implements QphMailSender {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String DATE_PART = "<date>";
    private static final String TODAY;
    static{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        TODAY = simpleDateFormat.format(new Date());
    }

    private JavaMailSenderImpl mailSender;

    private QphMailSenderProperties senderProperties;

    @Autowired
    public QphMailSenderImpl(@Qualifier(value = "otherMailSender")JavaMailSenderImpl mailSender, QphMailSenderProperties senderProperties)
    {
        this.mailSender = mailSender;
        this.senderProperties = senderProperties;
    }

    @Override
    public void sendMail() throws MessagingException {
        MimeMessage mimeMessage = prepareMimeMessage();
        this.mailSender.send(mimeMessage);
    }

    private MimeMessage prepareMimeMessage() throws MessagingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(senderProperties.getSender());
        mimeMessageHelper.setTo(senderProperties.getReceivers());
        mimeMessageHelper.setSubject(senderProperties.getSubject());
        mimeMessageHelper.setText(senderProperties.getBody(), true);
        mimeMessageHelper.addAttachment(senderProperties.getAttachmentFileName().replace(DATE_PART, TODAY),
                new FileSystemResource(senderProperties.getAttachmentFilePath().replace(DATE_PART, TODAY)));
        return mimeMessage;
    }

    @Override
    public void sendMail(String receivers) throws MessagingException
    {
        final MimeMessage mimeMessage = prepareMimeMessage();
        mimeMessage.addRecipients(Message.RecipientType.TO, receivers);
        this.mailSender.send(mimeMessage);
    }

}
