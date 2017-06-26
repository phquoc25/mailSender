package com.qph;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by quoc on 24/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class QphMailSenderTest {

    @InjectMocks
    @Autowired
    private QphMailSender qphMailSender;
    @Mock
    private MailSender mailSender;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

    }

    @Test
    public void sendMail() throws Exception {
        /*doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        qphMailSender.sendMail();
        verify(mailSender, atLeastOnce()).send(any(SimpleMailMessage.class));*/
    }

}