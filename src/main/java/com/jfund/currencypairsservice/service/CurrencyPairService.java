package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class CurrencyPairService {
    private final CurrencyPairRepository currencyPairRepository;

    public CurrencyPairService(CurrencyPairRepository currencyPairRepository) {
        this.currencyPairRepository = currencyPairRepository;
    }

    /**
     * Overwrites currency pairs.
     * In this case, currency pairs missing in currencyPairsToSave are deleted
     */
    public Mono<Void> saveCurrentAndDeleteOther(List<CurrencyPair> currencyPairsToSave){

        return currencyPairRepository.findAll()
                .collectList()
                .flatMap(existCurrencyList -> {
                    Set<CurrencyPair> currencyToSaveSet = new HashSet<>(currencyPairsToSave);

                    Flux<CurrencyPair> newPairsFlux = Flux.fromIterable(currencyPairsToSave)
                            .filter(pair -> !existCurrencyList.contains(pair));
                    Flux<CurrencyPair> pairsToDeleteFlux = Flux.fromIterable(existCurrencyList)
                            .filter(existPair -> !currencyToSaveSet.contains(existPair));

                    Mono<Void> deleteAction = newPairsFlux.collectList().flatMap(currencyPairRepository::deleteAll);
                    Mono<Void> createAction = pairsToDeleteFlux.collectList().flatMap(pairs -> {
                        currencyPairRepository.saveAll(pairs);
                        return null;
                    });

                    return Mono.empty().thenEmpty(createAction)
                            .then(Mono.empty().thenEmpty(deleteAction));
                });

    }

}
