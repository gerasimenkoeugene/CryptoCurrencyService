package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.SpringAppTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringAppTest.class)
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendMessage() {
        emailService.sendMonitoringResults("testReciever@mai.ru", "test message", new ArrayList<>());
    }
}
