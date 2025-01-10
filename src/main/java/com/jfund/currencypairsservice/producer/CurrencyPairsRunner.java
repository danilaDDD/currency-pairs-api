package com.jfund.currencypairsservice.producer;

import com.jfund.currencypairsservice.data.CurrencyPairsProducerData;
import com.jfund.currencypairsservice.service.CurrencyPairsProducerDataService;
import com.jfund.currencypairsservice.settings.ProducerSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyPairsRunner implements Runnable {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CurrencyPairsProducerDataService currencyPairsProducerDataService;
    private final ProducerSettings producerSettings;

    /**
     * serialized object:
     * {"serializedData":"[\"EURUSD\",\"EURJPG\",\"USDJPG\",\"USDEUR\",\"EURAUD\",\"USDAUD\",\"AURGBP\"]",
     * "empty":false,
     * "createdAt":"2025-01-09T18:36:15.890916053"}
     */

    public void run() {
        Mono<CurrencyPairsProducerData> producerDataMono = currencyPairsProducerDataService.getSerializedData();
        producerDataMono
                .doOnError(e -> log.error(e.getMessage()))
                .subscribe(producerData ->
                kafkaTemplate.send(producerSettings.getTopic(), producerData.getSerializedData()));
    }
}
