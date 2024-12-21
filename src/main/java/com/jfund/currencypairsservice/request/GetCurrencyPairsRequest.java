package com.jfund.currencypairsservice.request;

import com.jfund.currencypairsservice.exceptions.CurrencyApiRequestException;
import com.jfund.currencypairsservice.model.CurrencyPair;
import currencyapi.api.AsyncCurrencyApi;
import currencyapi.data.ApiCurrencyPair;
import currencyapi.exceptions.CurrencyRequestException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class  GetCurrencyPairsRequest{
    private final AsyncCurrencyApi asyncCurrencyApi = AsyncCurrencyApi.open();

    public List<CurrencyPair> getCurrencyPairs() {
        try {
            List<ApiCurrencyPair> apiCurrencyPairs = asyncCurrencyApi.getCurrencyPairs().get();
            return apiCurrencyPairs.stream()
                    .map(GetCurrencyPairsRequest::map)
                    .toList();

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (CurrencyRequestException e) {
            throw new CurrencyApiRequestException(e);
        }

    }

    private static CurrencyPair map(ApiCurrencyPair apiPair) {
        return new CurrencyPair(apiPair.fromCurrency(), apiPair.toCurrency(), true);
    }
}
