package com.jfund.currencypairsservice.request;

import com.jfund.currencypairsservice.model.CurrencyPair;
import currencyapi.exceptions.CurrencyBadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public interface GetCurrencyValuesFromRequest {
    Map<String, Float> getCurrencyValues(List<String> currencyKeys) throws CurrencyBadRequestException, ExecutionException, InterruptedException;
}
