package com.jfund.currencypairsservice.config;

import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import com.jfund.currencypairsservice.request.SyncGetCurrencyPairsRequest;
import com.jfund.currencypairsservice.saver.CurrencyPairsFromKeysSaver;
import com.jfund.currencypairsservice.saver.SimpleCurrencyPairsFromKeysSaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public GetCurrencyPairsRequest getCurrencyPairsRequest(){
        return new SyncGetCurrencyPairsRequest();
    }

//    @Bean
//    public CurrencyPairsFromKeysSaver currencyPairsFromKeysSaver(){
//        return new SimpleCurrencyPairsFromKeysSaver();
//    }
}
