package com.iege.cryptocurrency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptoCurrencyDTO {

    private String id;

    private String name;
    private String symbol;
    private String rank;
    @JsonProperty("price_usd")
    private String priceUSD;
    @JsonProperty("price_btc")
    private String priceBTC;
    @JsonProperty("last_updated")
    private Long lastUpdated;
    @JsonProperty("percent_change_1h")
    private Double percentChange1h;
    @JsonProperty("percent_change_24h")
    private Double percentChange24h;
    @JsonProperty("percent_change_7d")
    private Double percentChange7d;

}
