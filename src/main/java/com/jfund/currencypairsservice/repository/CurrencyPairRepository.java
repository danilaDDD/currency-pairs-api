package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.model.CurrencyPair;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CurrencyPairRepository extends MongoRepository<CurrencyPair, String> {
    List<CurrencyPair> findCurrencyPairByShowInCandle(boolean showInCandle);
}
