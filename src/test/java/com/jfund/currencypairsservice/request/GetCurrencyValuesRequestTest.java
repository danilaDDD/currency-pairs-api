package com.jfund.currencypairsservice.request;

import currencyapi.exceptions.CurrencyBadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class GetCurrencyValuesRequestTest {
    private final GetCurrencyValuesFromRequest getCurrencyValuesFromRequest;
    @Autowired
    public GetCurrencyValuesRequestTest(GetCurrencyValuesFromRequest getCurrencyValuesFromRequest) {
        this.getCurrencyValuesFromRequest = getCurrencyValuesFromRequest;
    }

    @Test
    public void getCurrencyValuesTest() throws CurrencyBadRequestException, ExecutionException, InterruptedException {
        Map<String, Float> valueMap = this.getCurrencyValuesFromRequest.getCurrencyValues(List.of("USDRUB", "RUBUSD"));
        valueMap.get("USDRUB");
        valueMap.get("RUBUSD");
    }

    @Test
    public void getCurrencyValuesByInvalidCurrencyKeyTest() throws CurrencyBadRequestException, ExecutionException, InterruptedException {
        Map<String, Float> valueMap = this.getCurrencyValuesFromRequest.getCurrencyValues(List.of("USDRUB", "RUBUSD", "YYYYYYY"));
    }
}
