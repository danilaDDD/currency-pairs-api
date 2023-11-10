package com.jfund.currencypairsservice.settings;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Getter
@Component
public class ProducerSettings {
    private String topic = "currency-keys-topic";
}
