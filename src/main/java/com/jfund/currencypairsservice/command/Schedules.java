package com.jfund.currencypairsservice.command;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Schedules {
    private final KafkaSendCurrencyKeys kafkaSendCurrencyKeys;
    private final LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner;

//    @Scheduled(fixedRate = 50_000)
//    public void sendCurrencyKeysByKafka(){
//        kafkaSendCurrencyKeys.invoke();
//    }

//    @Scheduled(fixedRate = 50_00000)
//    public void loadCurrencyKeysFromApi(){
//        loadCurrencyPairsCliRunner.invoke();
//    }
}
