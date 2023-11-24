package com.jfund.currencypairsservice.request;

import com.jfund.currencypairsservice.exceptions.CurrencyApiRequestRuntimeException;
import currencyapi.exceptions.CurrencyBadRequestException;
import currencyapi.handler.GetAllCurrencyPairsRequestHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class SyncGetCurrencyPairsRequest implements GetCurrencyPairsRequest{
    private GetAllCurrencyPairsRequestHandler getAllCurrencyPairsRequestHandler;
    private String accessKey;

    @Value("${currencyApi.accessKey}")
    public void setAccessKey(String accessKey){
        this.accessKey = accessKey;
    }

    @Override
    public List<String> getCurrencyPairs() {
        try {
            return new GetAllCurrencyPairsRequestHandler(this.accessKey).handle();
        } catch (CurrencyBadRequestException | ExecutionException | InterruptedException e) {
            throw new CurrencyApiRequestRuntimeException(e);
        }
    }
}
