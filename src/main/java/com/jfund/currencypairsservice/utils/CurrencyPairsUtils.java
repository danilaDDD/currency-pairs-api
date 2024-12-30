package com.jfund.currencypairsservice.utils;

import com.jfund.currencypairsservice.model.CurrencyPair;
import reactor.core.publisher.Flux;

import java.util.List;

public class CurrencyPairsUtils {
    public static boolean equalsCurrencyPairLists(List<CurrencyPair> pairs1, List<CurrencyPair> pairs2){
        if(pairs1.size() != pairs2.size())
            return false;

        for (int i = 0; i < pairs1.size(); i++) {
            if(equalsByDate(pairs1.get(i), (pairs2.get(i))))
                return false;
        }

        return true;
    }

    public static boolean equalsByDate(CurrencyPair pair1, CurrencyPair pair2){
        return pair1.getKey().equals(pair2.getKey())
                && pair1.isShowInCandle() == pair2.isShowInCandle();
    }

    public static List<CurrencyPair> ofKeys(List<String> keys){
        return keys.stream()
                .map(CurrencyPair::ofCode)
                .toList();
    }

    public static List<CurrencyPair> ofKeys(String... keys){
        return ofKeys(List.of(keys));
    }

    public static Flux<CurrencyPair> fluxOfKeys(String... keys){
        return Flux.fromIterable(ofKeys(keys));
    }
}
