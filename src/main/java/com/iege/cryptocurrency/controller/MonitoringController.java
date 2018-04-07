package com.iege.cryptocurrency.controller;

import com.iege.cryptocurrency.entity.CryptoCurrency;
import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.enums.MonitoringCondition;
import com.iege.cryptocurrency.repository.CryptoCurrencyRepository;
import com.iege.cryptocurrency.repository.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monitorings")
public class MonitoringController {

    private MonitoringRepository monitoringRepository;
    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    public void setMonitoringRepository(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Monitoring> getAllUserMonitoring(@RequestParam String idUser){
        return monitoringRepository.findListByIdUser(idUser);
    }

    @RequestMapping(value = "",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Monitoring saveMonitoring(@RequestBody Monitoring monitoring){
        return monitoringRepository.save(monitoring);
   }

    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    @ResponseBody
    public void deleteMonitoring(@RequestParam String monitoringId){
        monitoringRepository.delete(monitoringId);
    }
}
