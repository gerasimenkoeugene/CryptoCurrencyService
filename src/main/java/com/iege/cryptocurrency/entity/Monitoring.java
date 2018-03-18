package com.iege.cryptocurrency.entity;

import com.iege.cryptocurrency.enums.MonitoringCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@AllArgsConstructor
public class Monitoring {
    @Id
    private String id;
    @DBRef
    private CryptoCurrency cryptoCurrency;
    private String idUser;
    private String userEmail;
    private MonitoringCondition monitoringCondition;
    private Integer conditionValue;
}
