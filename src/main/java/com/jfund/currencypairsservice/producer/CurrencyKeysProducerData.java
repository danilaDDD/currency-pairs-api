package com.jfund.currencypairsservice.producer;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CurrencyKeysProducerData {
    private String key = LocalDateTime.now().toString();
    private List<String> availableCurrencyKeys;

    public CurrencyKeysProducerData(List<String> availableCurrencyKeys) {
        this.availableCurrencyKeys = availableCurrencyKeys;
    }
}
