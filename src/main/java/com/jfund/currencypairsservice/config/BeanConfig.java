package com.jfund.currencypairsservice.config;

import com.jfund.currencypairsservice.producer.CurrencyKeysProducer;
import com.jfund.currencypairsservice.producer.SimpleCurrencyKeysProducer;
import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import com.jfund.currencypairsservice.request.SyncGetCurrencyPairsRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public GetCurrencyPairsRequest getCurrencyPairsRequest(){
        return new SyncGetCurrencyPairsRequest();
    }
    @Bean
    public CurrencyKeysProducer currencyKeysProducer(){
        return new SimpleCurrencyKeysProducer();
    }
}
