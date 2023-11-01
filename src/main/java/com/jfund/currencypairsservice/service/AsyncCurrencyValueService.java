package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.model.CurrencyValue;
import com.jfund.currencypairsservice.repository.CurrencyValueRepository;
import com.jfund.datautils.service.BaseCrudAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncCurrencyValueService extends BaseCrudAsyncService<CurrencyValue, Long> {
    @Autowired
    public AsyncCurrencyValueService(CurrencyValueRepository currencyValueRepository, JdbcTemplate jdbcTemplate) {
        super(currencyValueRepository);
    }

    public CurrencyValueRepository getCurrencyValueRepository(){
        return (CurrencyValueRepository) super.getBaseRepository();
    }

    public CompletableFuture<List<CurrencyValue>> findLastByDateTimeOfActuality(){
        return supplyAsyncList(() -> (getCurrencyValueRepository().findLastByOrderByDateTimeOfActuality()));
    }
}
