package com.iege.cryptocurrency.repository;

import com.iege.cryptocurrency.entity.Monitoring;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MonitoringRepository extends MongoRepository<Monitoring, String> {

    Monitoring findById(String id);
    List<Monitoring> findListByIdUser(String idUser);
    List<Monitoring> findListByUserEmail(String userEmail);
}
