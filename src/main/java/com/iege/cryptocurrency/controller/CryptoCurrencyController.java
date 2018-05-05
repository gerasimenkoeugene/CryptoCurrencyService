package com.iege.cryptocurrency.controller;

import com.iege.cryptocurrency.entity.CryptoCurrency;
import com.iege.cryptocurrency.repository.CryptoCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cryptocurrency")
@PreAuthorize("isAuthenticated()")
public class CryptoCurrencyController {
    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CryptoCurrency> getAll() {
        return cryptoCurrencyRepository.findAll();
    }
}
