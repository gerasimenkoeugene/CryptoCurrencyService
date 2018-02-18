package com.iege.cryptocurrency.scheduler;

import com.iege.cryptocurrency.dto.CryptoCurrencyDTO;
import com.iege.cryptocurrency.entity.CryptoCurrency;
import com.iege.cryptocurrency.repository.CryptoCurrencyRepository;
import com.iege.cryptocurrency.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTasks {


    @Autowired
    CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    Converter<CryptoCurrencyDTO, CryptoCurrency> converter;

    @Scheduled(fixedRate = 300000)
    public void updateCryptoCurrencyRate() {
        List<CryptoCurrencyDTO> cryptoCurrencyDTOList = cryptoCurrencyService.getAll();
        List<CryptoCurrency> cryptoCurrencyList = cryptoCurrencyDTOList.stream()
                .map(dto -> converter.convert(dto))
                .collect(Collectors.toList());
        cryptoCurrencyRepository.save(cryptoCurrencyList);
    }
}
