package com.iege.cryptocurrency.scheduler;

import com.iege.cryptocurrency.dto.CryptoCurrencyDTO;
import com.iege.cryptocurrency.entity.CryptoCurrency;
import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.repository.CryptoCurrencyRepository;
import com.iege.cryptocurrency.service.CryptoCurrencyService;
import com.iege.cryptocurrency.service.EmailService;
import com.iege.cryptocurrency.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTasks {
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final Converter<CryptoCurrencyDTO, CryptoCurrency> converter;
    private final MonitoringService monitoringService;
    private final EmailService emailService;

    @Autowired
    public ScheduledTasks(CryptoCurrencyRepository cryptoCurrencyRepository, CryptoCurrencyService cryptoCurrencyService, Converter<CryptoCurrencyDTO, CryptoCurrency> converter, MonitoringService monitoringService, EmailService emailService) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.converter = converter;
        this.monitoringService = monitoringService;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 300000)
    public void updateCryptoCurrencyRate() {
        List<CryptoCurrencyDTO> cryptoCurrencyDTOList = cryptoCurrencyService.getAll();
        List<CryptoCurrency> cryptoCurrencyList = cryptoCurrencyDTOList.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        cryptoCurrencyRepository.save(cryptoCurrencyList);
    }

    @Scheduled(fixedRate = 300000)
    public void sentMonitoringResults() {
        List<String> usersEmail = monitoringService.getDistinctUserEmails();
        usersEmail.forEach(userEmail -> {
            List<Monitoring> monitorings = monitoringService.getUserMonitoringsWithTrueCondition(userEmail);
            if (!monitorings.isEmpty()) {
                emailService.sendMonitoringResults(userEmail, "Monitoring Results", monitorings);
            }
        });
    }
}
