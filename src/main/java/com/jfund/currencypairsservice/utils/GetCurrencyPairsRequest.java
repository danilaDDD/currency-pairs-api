package com.jfund.currencypairsservice.utils;

import com.jfund.currencypairsservice.model.CurrencyPair;
import currencyapi.api.AsyncCurrencyApi;
import currencyapi.data.ApiCurrencyPair;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class  GetCurrencyPairsRequest{
    private final AsyncCurrencyApi asyncCurrencyApi = AsyncCurrencyApi.open();

    public Flux<CurrencyPair> getCurrencyPairsFlux() {
        var currencyPairsListFuture = asyncCurrencyApi.getCurrencyPairs();

        return Mono.fromFuture(currencyPairsListFuture)
                .map(apiCurrencyPairs -> apiCurrencyPairs.stream().map(GetCurrencyPairsRequest::map).toList())
                .flatMapMany(Flux::fromIterable);


    }

    private static CurrencyPair map(ApiCurrencyPair apiPair) {
        return new CurrencyPair(apiPair.fromCurrency(), apiPair.toCurrency(), true);
    }
}
