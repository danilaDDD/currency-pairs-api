package com.jfund.currencypairsservice.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class Schedules {
    private LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner;
    private KafkaSendCurrencyKeys kafkaSendCurrencyKeys;

    @Autowired
    public void setLoadCurrencyValuesCliRunner(LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner) {
        this.loadCurrencyValuesCliRunner = loadCurrencyValuesCliRunner;
    }

    @Autowired
    public void setKafkaSendCurrencyKeys(KafkaSendCurrencyKeys kafkaSendCurrencyKeys) {
        this.kafkaSendCurrencyKeys = kafkaSendCurrencyKeys;
    }

    public void sendCurrencyKeysValueByMessaging(){
        this.kafkaSendCurrencyKeys.invoke();
    }
}
