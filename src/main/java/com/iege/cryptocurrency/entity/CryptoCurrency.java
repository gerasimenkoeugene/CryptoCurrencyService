package com.iege.cryptocurrency.entity;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
public class CryptoCurrency {

    @Id
    private String id;

    private String name;
    private String symbol;
    private String rank;
    private String priceUSD;
    private String priceBTC;
    private Long lastUpdated;
    private Double percentChange1h;
    private Double percentChange24h;
    private Double percentChange7d;

}
