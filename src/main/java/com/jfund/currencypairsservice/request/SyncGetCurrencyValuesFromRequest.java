package com.jfund.currencypairsservice.request;

import currencyapi.exceptions.CurrencyBadRequestException;
import currencyapi.handler.GetAllCurrencyValuesRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class SyncGetCurrencyValuesFromRequest implements GetCurrencyValuesFromRequest{
    @Value("${currencyApi.accessKey}")
    private String accessToken;
    @Override
    public Map<String, Float> getCurrencyValues(List<String> currencyKeys) throws CurrencyBadRequestException, ExecutionException, InterruptedException {
        return new GetAllCurrencyValuesRequestHandler(this.accessToken, currencyKeys).handle();
    }
}
