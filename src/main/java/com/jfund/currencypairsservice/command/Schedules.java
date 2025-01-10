package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.producer.CurrencyPairsRunner;
import com.jfund.currencypairsservice.runner.CurrencyPairsLoadRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Schedules {
    private final CurrencyPairsRunner kafkaSendCurrencyKeys;
    private final CurrencyPairsLoadRunner currencyPairsLoadRunner;

    @Scheduled(fixedRate = 50_0000)
    public void sendCurrencyKeysByKafka(){
        kafkaSendCurrencyKeys.run();
    }

    @Scheduled(fixedRate = 50_000000)
    public void loadCurrencyKeysFromApi(){
        currencyPairsLoadRunner.run();
    }
}
