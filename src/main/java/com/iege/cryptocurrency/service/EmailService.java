package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.entity.Monitoring;

import java.util.List;

public interface EmailService {
    void sendMonitoringResults(String to, String subject, List<Monitoring> monitorings);
}
