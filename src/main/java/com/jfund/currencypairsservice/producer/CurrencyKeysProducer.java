package com.jfund.currencypairsservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfund.currencypairsservice.exceptions.CurrencyKeyProducerRuntimeException;
import com.jfund.currencypairsservice.settings.ProducerSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyKeysProducer{
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ProducerSettings producerSettings;

    public void sendCurrencyKeys(CurrencyKeysProducerData currencyKeysProducerData) {
        try {
            List<String> currencyKeys = currencyKeysProducerData.getAvailableCurrencyKeys();
            String serializedCurrencyKeys = new ObjectMapper().writeValueAsString(currencyKeys);
            kafkaTemplate.send(producerSettings.getTopic(), serializedCurrencyKeys);
        } catch (JsonProcessingException e) {
            throw new CurrencyKeyProducerRuntimeException(e);
        }
    }
}
