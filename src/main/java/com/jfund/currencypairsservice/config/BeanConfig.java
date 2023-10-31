package com.jfund.currencypairsservice.config;

import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import com.jfund.currencypairsservice.request.GetCurrencyValuesFromRequest;
import com.jfund.currencypairsservice.request.SyncGetCurrencyPairsRequest;
import com.jfund.currencypairsservice.request.SyncGetCurrencyValuesFromRequest;
import com.jfund.currencypairsservice.saver.CurrencyPairsFromKeysSaver;
import com.jfund.currencypairsservice.saver.CurrencyValuesSaver;
import com.jfund.currencypairsservice.saver.SimpleCurrencyPairsFromKeysSaver;
import com.jfund.currencypairsservice.saver.SimpleCurrencyValuesSaver;
import com.jfund.currencypairsservice.service.AsyncCurrencyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public GetCurrencyPairsRequest getCurrencyPairsRequest(){
        return new SyncGetCurrencyPairsRequest();
    }
    @Bean
    public GetCurrencyValuesFromRequest getCurrencyValuesFromRequest(){
        return new SyncGetCurrencyValuesFromRequest();
    }
    @Bean
    public CurrencyValuesSaver currencyValuesSaver(){
        return new SimpleCurrencyValuesSaver();
    }
}
