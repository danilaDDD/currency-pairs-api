package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class ReplaceCurrencyPairService {
    private static final Comparator<CurrencyPair> BY_KEY_COMPARATOR = Comparator.comparing(CurrencyPair::getKey);

    private final CurrencyPairRepository currencyPairRepository;

    public ReplaceCurrencyPairService(CurrencyPairRepository currencyPairRepository) {
        this.currencyPairRepository = currencyPairRepository;
    }

    public Flux<CurrencyPair> findActiveAll() {
        return currencyPairRepository.findAllByShowInCandleTrue();
    }

    /**
     * Overwrites currency pairs.
     * In this case, currency pairs missing in currencyPairsToSave are deleted
     */
    public Mono<Void> replaceCurrencyPairs(Flux<CurrencyPair> currencyPairsToSave) {
        if (currencyPairsToSave == null) {
            return Mono.error(new IllegalArgumentException("Currency pairs to save are null"));
        }

        return currencyPairRepository.findAll().collectList()
                .flatMap(existingCurrencyPairs -> currencyPairsToSave.collectList()
                        .flatMap(newCurrencyPairs -> replaceCurrencyPairs(existingCurrencyPairs, newCurrencyPairs)));
    }

    private Mono<Void> replaceCurrencyPairs(List<CurrencyPair> existingCurrencyPairs, List<CurrencyPair> newCurrencyPairs) {
        if (newCurrencyPairs.isEmpty()) {
            return Mono.error(new IllegalArgumentException("Currency pairs to save are empty"));
        }

        Set<CurrencyPair> existingCurrencyPairsSet = fillSetAndGet(existingCurrencyPairs);
        Set<CurrencyPair> newCurrencyPairsSet = fillSetAndGet(newCurrencyPairs);

        Set<CurrencyPair> currencyPairsToDelete = fillSetAndGet(existingCurrencyPairsSet);
        currencyPairsToDelete.removeAll(newCurrencyPairsSet);

        Set<CurrencyPair> currencyPairsToSave = fillSetAndGet(newCurrencyPairsSet);
        currencyPairsToSave.removeAll(existingCurrencyPairsSet);

        Mono<Void> createAction, deleteAction;
        createAction = !currencyPairsToSave.isEmpty() ? currencyPairRepository.saveAll(currencyPairsToSave).then() : Mono.empty();
        deleteAction = !currencyPairsToDelete.isEmpty() ? currencyPairRepository.deleteAll(currencyPairsToDelete).then() : Mono.empty();

        return createAction.and(deleteAction);
    }

    private Set<CurrencyPair> fillSetAndGet(Collection<CurrencyPair> currencyPairs) {
        Set<CurrencyPair> result = new TreeSet<>(BY_KEY_COMPARATOR);
        result.addAll(currencyPairs);
        return result;
    }


}
