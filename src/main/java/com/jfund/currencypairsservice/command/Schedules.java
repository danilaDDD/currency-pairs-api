package com.jfund.currencypairsservice.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedules {
    private LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner;
    @Autowired
    public void setLoadCurrencyValuesCliRunner(LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner) {
        this.loadCurrencyValuesCliRunner = loadCurrencyValuesCliRunner;
    }
    @Scheduled(fixedRate = 5000)
    public void loadCurrencyValuesFromCurrencyApi(){
        loadCurrencyValuesCliRunner.invoke();
    }

}
