package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.model.CurrencyPair;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CurrencyPairRepository extends ReactiveCrudRepository<CurrencyPair, Long> {
    Flux<CurrencyPair> findAllByShowInCandleTrue();
}
