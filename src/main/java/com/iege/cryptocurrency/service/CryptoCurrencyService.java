package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.dto.CryptoCurrencyDTO;

import java.util.List;

public interface CryptoCurrencyService {
    List<CryptoCurrencyDTO> getAll();
    CryptoCurrencyDTO getById(String id);
}
