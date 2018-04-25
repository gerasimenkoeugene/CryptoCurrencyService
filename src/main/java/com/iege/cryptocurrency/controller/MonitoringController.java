package com.iege.cryptocurrency.controller;

import com.iege.cryptocurrency.entity.Monitoring;
import com.iege.cryptocurrency.repository.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monitorings")
public class MonitoringController {

    private MonitoringRepository monitoringRepository;

    @Autowired
    public void setMonitoringRepository(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<Monitoring> getAllUserMonitoring(@RequestParam String idUser){
        return monitoringRepository.findListByIdUser(idUser);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Monitoring getById(@RequestParam String monitoringId){
        return monitoringRepository.findById(monitoringId);
    }

    @RequestMapping(value = "",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Monitoring saveMonitoring(@RequestBody Monitoring monitoring){
        return monitoringRepository.save(monitoring);
   }

    @RequestMapping(method= RequestMethod.DELETE)
    @ResponseBody
    public void deleteMonitoring(@RequestParam String monitoringId){
        monitoringRepository.delete(monitoringId);
    }

    @RequestMapping(value = "/deactivate", method = RequestMethod.DELETE)
    @ResponseBody
    public void deactivateMonitoring(@RequestParam String monitoringId){
        Monitoring monitoring = monitoringRepository.findById(monitoringId);
        monitoring.setActive(false);
        monitoringRepository.save(monitoring);
    }

    @RequestMapping(value = "/user/deactivate", method = RequestMethod.DELETE)
    @ResponseBody
    public void deactivateUserMonitoring(@RequestParam String idUser){
        List<Monitoring> monitorings = monitoringRepository.findListByIdUser(idUser);
        monitorings.forEach(monitoring -> monitoring.setActive(false));
        monitoringRepository.save(monitorings);
    }
}
