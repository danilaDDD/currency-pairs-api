package com.jfund.currencypairsservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfund.currencypairsservice.settings.ProducerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class SimpleCurrencyKeysProducer implements CurrencyKeysProducer{
    private KafkaTemplate<String, String> kafkaTemplate;
    private ProducerSettings producerSettings;

    private ObjectMapper objectMapper;
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @Autowired
    public void setProducerSettings(ProducerSettings producerSettings) {
        this.producerSettings = producerSettings;
    }
    @Override
    public void sendCurrencyKeys(CurrencyKeysProducerData currencyKeysProducerData) throws ExecutionException, InterruptedException, JsonProcessingException {
        List<String> currencyKeys = currencyKeysProducerData.getAvailableCurrencyKeys();
        String serializedCurrencyKeys = new ObjectMapper().writeValueAsString(currencyKeys);
        kafkaTemplate.send(producerSettings.getTopic(), serializedCurrencyKeys);
    }
}
