package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.model.CurrencyPair;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CurrencyPairRepository extends CrudRepository<CurrencyPair, String> {
    List<CurrencyPair> findCurrencyPairByShowInCandle(boolean showInCandle);
    List<CurrencyPair> deleteCurrencyPairByKeyIn(Collection<String> key);
}
