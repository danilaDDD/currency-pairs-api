package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.producer.CurrencyKeysProducer;
import com.jfund.currencypairsservice.producer.CurrencyKeysProducerData;
import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j(topic = "errors")
@Component
@RequiredArgsConstructor
public class KafkaSendCurrencyKeys{
    private final CurrencyKeysProducer currencyKeysProducer;
    private final GetCurrencyPairsRequest getCurrencyPairsRequest;

    public void invoke() {

        try {
            List<String> currencyKeys = this.getCurrencyPairsRequest.getCurrencyPairs().stream()
                    .map(CurrencyPair::getKey)
                    .toList();

            this.currencyKeysProducer.sendCurrencyKeys(new CurrencyKeysProducerData(currencyKeys));

        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
