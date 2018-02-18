package com.iege.cryptocurrency.repository;


import com.iege.cryptocurrency.entity.CryptoCurrency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {

    public CryptoCurrency findById(String cryptoName);
    public List<CryptoCurrency> findListByName(String cryptoName);

}
