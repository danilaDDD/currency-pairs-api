package com.jfund.currencypairsservice.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProducerSettings {
    @Value("${app.kafka.currency-keys-topic}")
    private String currencyKeysTopic;

    @Value("${app.kafka.server}")
    private String kafkaServer;
}
