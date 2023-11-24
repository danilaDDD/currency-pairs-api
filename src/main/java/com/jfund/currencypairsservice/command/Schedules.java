package com.jfund.currencypairsservice.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Schedules {
    private KafkaSendCurrencyKeys kafkaSendCurrencyKeys;
    @Autowired
    public void setKafkaSendCurrencyKeys(KafkaSendCurrencyKeys kafkaSendCurrencyKeys) {
        this.kafkaSendCurrencyKeys = kafkaSendCurrencyKeys;
    }
}
