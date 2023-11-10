package com.jfund.currencypairsservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public interface CurrencyKeysProducer {
    void sendCurrencyKeys(CurrencyKeysProducerData currencyKeysProducerData) throws ExecutionException, InterruptedException, JsonProcessingException;
}
