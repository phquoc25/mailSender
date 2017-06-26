package com.qph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Arrays;
import java.util.Properties;

import javax.mail.MessagingException;

/**
 * Created by quoc on 24/06/2017.
 */
@Configuration
@ImportResource("classpath:properties-config.xml")
@ComponentScan(value = {"com.qph"})
public class AppConfiguration {
    @Autowired
    private QphMailSenderProperties qphMailSenderProperties;

    @Bean
    public JavaMailSenderImpl gmailSender()
    {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(qphMailSenderProperties.getGmailUserName());
        javaMailSender.setPassword(qphMailSenderProperties.getGmailPassword());

        Properties javaMailProperties = getJavaMailProperties();
        javaMailSender.setJavaMailProperties(javaMailProperties);

        return javaMailSender;
    }

    @Bean
    public JavaMailSenderImpl otherMailSender()
    {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(qphMailSenderProperties.getHost());
        javaMailSender.setPort(Integer.valueOf(qphMailSenderProperties.getPort()));
        javaMailSender.setUsername(qphMailSenderProperties.getUserName());
        javaMailSender.setPassword(qphMailSenderProperties.getPassword());

        Properties javaMailProperties = getJavaMailProperties();
        javaMailSender.setJavaMailProperties(javaMailProperties);

        return javaMailSender;
    }

    private Properties getJavaMailProperties()
    {
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        return javaMailProperties;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QphMailSender qphMailSender = applicationContext.getBean(QphMailSender.class);
        try {
            qphMailSender.sendMail();
            System.out.println("Email sending successfully!");
        } catch (MessagingException e) {
            System.out.println("Error when preparing message");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
