package com.jfund.currencypairsservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfund.currencypairsservice.data.CurrencyPairsProducerData;
import com.jfund.currencypairsservice.model.CurrencyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyPairsProducerDataService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CurrencyPairCRUDService currencyPairCRUDService;

    public Mono<CurrencyPairsProducerData> getSerializedData() {
            return currencyPairCRUDService.findActiveAll()
                .map(CurrencyPair::getKey)
                .collectList().flatMap(keys -> {
                    if (keys.isEmpty()) {
                        return Mono.just(new CurrencyPairsProducerData("[]", true));
                    }
                    return Mono.just(new CurrencyPairsProducerData(serializedData(keys)));
                });
    }

    private String serializedData(List<String> keys) {
        try {
            return OBJECT_MAPPER.writeValueAsString(keys);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
