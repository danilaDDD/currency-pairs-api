package com.jfund.currencypairsservice.runner;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.service.LoadCurrencyPairsService;
import com.jfund.currencypairsservice.service.ReplaceCurrencyPairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyPairsLoadRunner {
    private final ReplaceCurrencyPairService replaceCurrencyPairService;
    private final LoadCurrencyPairsService loadCurrencyPairsService;

    public void run() {
        Flux<CurrencyPair> currencyPairsFromApi = loadCurrencyPairsService.getCurrencyPairsFlux();
        replaceCurrencyPairService.replaceCurrencyPairs(currencyPairsFromApi)
                .doOnError(e -> log.error(e.getMessage()))
                .subscribe();
    }
}
