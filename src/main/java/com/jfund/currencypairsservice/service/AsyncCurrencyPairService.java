package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import com.jfund.datautils.service.BaseCrudAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncCurrencyPairService extends BaseCrudAsyncService<CurrencyPair, String> {

    @Autowired
    public AsyncCurrencyPairService(CurrencyPairRepository baseRepository) {
        super(baseRepository);
    }

    public CompletableFuture<List<CurrencyPair>> findByShowInCandle(boolean showInCandle){
        return supplyAsyncList(() -> (getCurrencyPairRepository().findCurrencyPairByShowInCandle(showInCandle)));
    }

    public CompletableFuture<List<CurrencyPair>> deleteByKeys(Set<String> currencyKeys){
        return supplyAsyncList(() -> (getCurrencyPairRepository().deleteCurrencyPairByKeyIn(currencyKeys)));
    }

    public CompletableFuture<List<CurrencyPair>> findToCandleApi(){
        return supplyAsyncList(() -> (getCurrencyPairRepository().findCurrencyPairByShowInCandle(true)));
    }

    public CurrencyPairRepository getCurrencyPairRepository(){
        return (CurrencyPairRepository)getBaseRepository();
    }

}
