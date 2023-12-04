package com.jfund.currencypairsservice.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Schedules {
    private KafkaSendCurrencyKeys kafkaSendCurrencyKeys;
    @Autowired
    public void setKafkaSendCurrencyKeys(KafkaSendCurrencyKeys kafkaSendCurrencyKeys) {
        this.kafkaSendCurrencyKeys = kafkaSendCurrencyKeys;
    }
    @Scheduled(fixedRate = 50000)
    public void sendCurrencyKeysByKafka(){
        kafkaSendCurrencyKeys.invoke();
    }
}
