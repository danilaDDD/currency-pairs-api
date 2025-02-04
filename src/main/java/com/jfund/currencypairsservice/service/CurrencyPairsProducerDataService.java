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
    private final ObjectMapper objectMapper;
    private final CurrencyPairCRUDService currencyPairCRUDService;

    public Mono<String> getSerializedData() {
            return currencyPairCRUDService.findActiveAll()
                .map(CurrencyPair::getKey)
                .collectList().flatMap(keys -> {
                    try{
                        CurrencyPairsProducerData currencyPairsProducerData;
                        if (keys.isEmpty()) {
                            currencyPairsProducerData = new CurrencyPairsProducerData("[]", true);
                        }else {
                            currencyPairsProducerData = new CurrencyPairsProducerData(serializedData(keys));
                        }
                        return Mono.just(objectMapper
                                .writeValueAsString(currencyPairsProducerData));
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException(e));
                    }
                });
    }

    private String serializedData(List<String> keys) {
        try {
            return objectMapper.writeValueAsString(keys);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
