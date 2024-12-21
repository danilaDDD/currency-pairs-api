package com.jfund.currencypairsservice.settings;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ProducerSettings {
    private final String topic = "currency-keys-topic";
}
