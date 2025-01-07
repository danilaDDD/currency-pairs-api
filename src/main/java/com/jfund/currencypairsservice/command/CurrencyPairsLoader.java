package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.service.LoadCurrencyPairsService;
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
    private final LoadCurrencyPairsService loadCurrencyPairsService;

    public void invoke() {
        Flux<CurrencyPair> currencyPairsFromApi = loadCurrencyPairsService.getCurrencyPairsFlux();
        replaceCurrencyPairService.replaceCurrencyPairs(currencyPairsFromApi).subscribe();
    }
}
