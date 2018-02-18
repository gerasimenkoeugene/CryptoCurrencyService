package com.iege.cryptocurrency.service;

import com.iege.cryptocurrency.dto.CryptoCurrencyDTO;

import java.util.List;

public interface CryptoCurrencyService {
    public List<CryptoCurrencyDTO> getAll();
    public CryptoCurrencyDTO getById(String id);

}
