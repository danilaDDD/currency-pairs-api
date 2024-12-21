package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.model.CurrencyPair;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CurrencyPairRepository extends ReactiveCrudRepository<CurrencyPair, Long> {
}
