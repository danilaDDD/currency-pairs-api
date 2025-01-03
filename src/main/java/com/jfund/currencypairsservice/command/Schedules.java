package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.producer.CurrencyPairsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Schedules {
    private CurrencyPairsProducer kafkaSendCurrencyKeys;
    private final CurrencyPairsLoader currencyPairsLoader;

//    @Scheduled(fixedRate = 50_000)
//    public void sendCurrencyKeysByKafka(){
//        kafkaSendCurrencyKeys.invoke();
//    }

//    @Scheduled(fixedRate = 50_00000)
//    public void loadCurrencyKeysFromApi(){
//        loadCurrencyPairsCliRunner.invoke();
//    }
}
