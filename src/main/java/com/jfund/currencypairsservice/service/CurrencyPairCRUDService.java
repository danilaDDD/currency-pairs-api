package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CurrencyPairCRUDService {
    private final CurrencyPairRepository currencyPairRepository;

    public Flux<CurrencyPair> findActiveAll() {
        return currencyPairRepository.findAllByShowInCandleTrue();
    }
}
