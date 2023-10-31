package com.jfund.currencypairsservice.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class CurrencyValueTest {
    @Test
    public void testCreateEntity(){
        List<CurrencyValue> currencyValues = List.of(
                new CurrencyValue(),
                new CurrencyValue("USDJPG", 96.234F, LocalDateTime.now()),
                new CurrencyValue()
                        .setKey("USDGBP")
                        .setValue(102.02F)
                        .setDateTimeOfActuality(LocalDateTime.now())
                        .setId(2L)
                        .setSentToCandleApi(false)
                );

        currencyValues.forEach(currencyValue -> currencyValue.setSentToCandleApi(true));
    }
}
