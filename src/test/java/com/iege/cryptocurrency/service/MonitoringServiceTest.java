package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.SpringAppTest;
import com.iege.cryptocurrency.entity.CryptoCurrency;
import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.enums.MonitoringCondition;
import com.iege.cryptocurrency.repository.MonitoringRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringAppTest.class)
public class MonitoringServiceTest {

    private final String MONITORING_ID = "1";
    private final String USER_EMAIL = "test@mail.ru";
    @Autowired
    private MonitoringService monitoringService;
    @Autowired
    private MonitoringRepository monitoringRepository;
    private Monitoring monitoring;

    @Before
    public void setUp() {
        CryptoCurrency btc = new CryptoCurrency("bitcoin", "Bitcoin", "BTC", "1", "9920.26", "1", 1525518572L, 0.6, 0.6, 0.6);
        monitoring = new Monitoring(MONITORING_ID, btc, "1", USER_EMAIL, MonitoringCondition.MORE_THEN_USD, 100.0, true);
        monitoringRepository.save(monitoring);
    }

    @Test
    public void tesGetDistinctUserEmails() {
        assertEquals(true, monitoringService.getDistinctUserEmails().contains(USER_EMAIL));
    }

    @Test
    public void testGetUserMonitoringsWithTrueCondition() {
        assertEquals(1, monitoringService.getUserMonitoringsWithTrueCondition(USER_EMAIL).size());
    }

    @Test
    public void testIsConditionTrue() {
        assertEquals(true, monitoringService.isConditionTrue(monitoring));
    }

    @After
    public void claenUp() {
        monitoringRepository.delete(monitoring);
    }

}
