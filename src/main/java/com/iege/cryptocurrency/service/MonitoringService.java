package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.entity.Monitoring;

import java.util.List;

public interface MonitoringService {
    List<String> getDistinctUserEmails();
    List<Monitoring> getUserMonitoringsWithTrueCondition(String userEmail);
    boolean isConditionTrue(Monitoring monitoring);
}
