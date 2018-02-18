package com.iege.cryptocurrency.converter;

import com.iege.cryptocurrency.dto.CryptoCurrencyDTO;
import com.iege.cryptocurrency.entity.CryptoCurrency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CryptoCurrencyConverter implements Converter<CryptoCurrencyDTO, CryptoCurrency>{
    @Override
    public CryptoCurrency convert(CryptoCurrencyDTO cryptoCurrencyDTO) {
        return
                new CryptoCurrency(
                        cryptoCurrencyDTO.getId(),
                        cryptoCurrencyDTO.getName(),
                        cryptoCurrencyDTO.getSymbol(),
                        cryptoCurrencyDTO.getRank(),
                        cryptoCurrencyDTO.getPriceUSD(),
                        cryptoCurrencyDTO.getPriceBTC(),
                        cryptoCurrencyDTO.getLastUpdated(),
                        cryptoCurrencyDTO.getPercentChange1h(),
                        cryptoCurrencyDTO.getPercentChange24h(),
                        cryptoCurrencyDTO.getPercentChange7d()
                );
    }
}
