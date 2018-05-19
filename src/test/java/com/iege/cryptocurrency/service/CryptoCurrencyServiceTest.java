package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.SpringAppTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringAppTest.class)
public class CryptoCurrencyServiceTest {

    @Autowired
    private
    CryptoCurrencyService cryptoCurrencyService;

    @Test
    public void testGetAll() {
        assertThat(!cryptoCurrencyService.getAll().isEmpty());
    }

    @Test
    public void testGetById() {
        assertThat(!cryptoCurrencyService.getById("bitcoin").getSymbol().equals("BTC"));
    }
}
