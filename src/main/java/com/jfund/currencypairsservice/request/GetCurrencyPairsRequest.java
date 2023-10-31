package com.jfund.currencypairsservice.request;

import currencyapi.exceptions.CurrencyBadRequestException;
import currencyapi.handler.CurrencyRequestHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Component
public interface GetCurrencyPairsRequest{
    List<String> getCurrencyPairs() throws CurrencyBadRequestException, ExecutionException, InterruptedException;
}
