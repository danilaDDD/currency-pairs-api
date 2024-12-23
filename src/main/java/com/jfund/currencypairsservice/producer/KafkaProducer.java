package com.jfund.currencypairsservice.producer;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.service.CurrencyPairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "errors")
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final CurrencyKeysProducer currencyKeysProducer;
    private final CurrencyPairService currencyPairService;
    // @FIXME specify exception classes
    public void invoke() {

        try {
            currencyPairService.findActiveAll().collectList()
                    .map(currencyPairs -> new CurrencyKeysProducerData(currencyPairs.stream().map(CurrencyPair::getKey)
                            .toList()))

                            .subscribe(currencyKeysProducer::sendCurrencyKeys);

        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
