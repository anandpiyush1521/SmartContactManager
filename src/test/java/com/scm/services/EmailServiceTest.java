package com.scm.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService service;

    @Test
    void testSendEmail(){
        service.sendEmail("whopiyushanand@gmail.com", 
            "Just Testing SCM Email service", 
            "This is a test email from SCM");
    }
}
