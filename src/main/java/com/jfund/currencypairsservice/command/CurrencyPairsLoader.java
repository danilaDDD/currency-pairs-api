package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.utils.GetCurrencyPairsRequest;
import com.jfund.currencypairsservice.service.ReplaceCurrencyPairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j(topic = "errors")
@Component
@RequiredArgsConstructor
public class CurrencyPairsLoader {
    private final ReplaceCurrencyPairService replaceCurrencyPairService;
    private final GetCurrencyPairsRequest getCurrencyPairsRequest;

    public void invoke() {
        Flux<CurrencyPair> currencyPairsFromApi = getCurrencyPairsRequest.getCurrencyPairsFlux();
        replaceCurrencyPairService.replaceCurrencyPairs(currencyPairsFromApi).subscribe();
    }
}
