package com.iege.cryptocurrency.service.impl;

import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.enums.MonitoringCondition;
import com.iege.cryptocurrency.repository.MonitoringRepository;
import com.iege.cryptocurrency.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitoringServiceImpl implements MonitoringService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MonitoringRepository monitoringRepository;

    @Override
    public List<String> getDistinctUserEmails() {
        return mongoTemplate.getCollection("monitoring").distinct("userEmail");
    }

    @Override
    public List<Monitoring> getUserMonitoringsWithTrueCondition(String userEmail) {
        List<Monitoring> monitorings = monitoringRepository.findListByUserEmail(userEmail);
        return monitorings.stream().filter(this::isConditionTrue).collect(Collectors.toList());

    }

    @Override
    public boolean isConditionTrue(Monitoring monitoring) {
        double price = Double.valueOf(monitoring.getCryptoCurrency().getPriceUSD());
        double conditionValue = monitoring.getConditionValue();
        return monitoring.getMonitoringCondition().equals(MonitoringCondition.LESS_THEN_USD) ? price < conditionValue : price > conditionValue && monitoring.getActive();
    }
}
