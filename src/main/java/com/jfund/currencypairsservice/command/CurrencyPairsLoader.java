package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import com.jfund.currencypairsservice.service.CurrencyPairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j(topic = "errors")
@Component
@RequiredArgsConstructor
public class CurrencyPairsLoader {
    private final CurrencyPairService currencyPairService;
    private final GetCurrencyPairsRequest getCurrencyPairsRequest;

    public void invoke() {
        List<CurrencyPair> currencyPairsFromApi = getCurrencyPairsRequest.getCurrencyPairs();
        currencyPairService.saveCurrentAndDeleteOther(currencyPairsFromApi).subscribe();
    }
}
