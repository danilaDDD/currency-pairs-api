package com.jfund.currencypairsservice.request;

import currencyapi.exceptions.CurrencyBadRequestException;
import currencyapi.handler.CurrencyRequestHandler;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetCurrencyPairsRequest{
    List<String> getCurrencyPairs() throws CurrencyBadRequestException, ExecutionException, InterruptedException;
}
