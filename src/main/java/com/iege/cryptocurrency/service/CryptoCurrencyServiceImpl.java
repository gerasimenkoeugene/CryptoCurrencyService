package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.dto.CryptoCurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CryptoCurrencyDTO> getAll() {
        return Arrays.asList(restTemplate.getForEntity("https://api.coinmarketcap.com/v1/ticker/", CryptoCurrencyDTO[].class).getBody());
    }

    @Override
    public CryptoCurrencyDTO getById(String id) {
        return restTemplate.getForEntity("https://api.coinmarketcap.com/v1/ticker/" + id, CryptoCurrencyDTO.class).getBody();
    }
}
