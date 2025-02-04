package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.producer.SendCurrencyPairsRunner;
import com.jfund.currencypairsservice.runner.LoadCurrencyPairsRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class Schedules {
    private final SendCurrencyPairsRunner kafkaSendCurrencyKeys;
    private final LoadCurrencyPairsRunner loadCurrencyPairsRunner;
    @Value("${app.scheduler.enabled}")
    private boolean enabled;

    @Scheduled(cron = "${app.scheduler.send-currency-keys}")
    public void sendCurrencyKeysByKafka(){
        if(enabled)
            kafkaSendCurrencyKeys.run();
    }

    @Scheduled(cron = "${app.scheduler.load-currency-keys}")
    public void loadCurrencyKeysFromApi(){
        if(enabled)
            loadCurrencyPairsRunner.run();
    }
}
