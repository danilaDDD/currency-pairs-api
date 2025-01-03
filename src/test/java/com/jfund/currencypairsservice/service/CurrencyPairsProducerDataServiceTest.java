package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.data.CurrencyPairsProducerData;
import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CurrencyPairsProducerDataServiceTest {
    private static final String[] KEYS = {"EURUSD", "EURJPG", "USDJPG", "USDEUR", "EURAUD", "USDAUD", "AURGBP"};
    @Autowired
    private CurrencyPairRepository  currencyPairRepository;
    @Autowired
    private CurrencyPairsProducerDataService currencyPairsProducerDataService;

    @BeforeEach
    void setUp() {
        currencyPairRepository.deleteAll().block();
    }

    @Test
    void testGet_WithExist_ShouldSerializedAll() {
        CurrencyPairsProducerData expected = new CurrencyPairsProducerData(serialize(KEYS));

        List<CurrencyPair> existPairList = Stream.of(KEYS).map(CurrencyPair::ofCode).toList();
        currencyPairRepository.saveAll(existPairList).blockLast();

        StepVerifier.create(currencyPairsProducerDataService.getSerializedData())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void testGet_WithNotExist_ShouldSerializedEmpty() {
        CurrencyPairsProducerData expected = new CurrencyPairsProducerData("[]", true);

        StepVerifier.create(currencyPairsProducerDataService.getSerializedData())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void testGet_WithSameNotActivePairs_ShouldSerializedActiveOnly() {
        List<CurrencyPair> pairs = Arrays.stream(KEYS).map(CurrencyPair::ofCode).toList();
        pairs.subList(0, 3).forEach(pair -> pair.setShowInCandle(false));
        currencyPairRepository.saveAll(pairs).blockLast();

        String[] activeKeys = Arrays.copyOfRange(KEYS, 3, KEYS.length);
        CurrencyPairsProducerData expected = new CurrencyPairsProducerData(serialize(activeKeys));

        StepVerifier.create(currencyPairsProducerDataService.getSerializedData())
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    void testGet_WithAllNotActivePairs_ShouldSerializedEmpty() {
        List<CurrencyPair> pairs = Arrays.stream(KEYS).map(CurrencyPair::ofCode).toList();
        pairs.forEach(pair -> pair.setShowInCandle(false));
        currencyPairRepository.saveAll(pairs).blockLast();

        CurrencyPairsProducerData expected = new CurrencyPairsProducerData("[]", true);

        StepVerifier.create(currencyPairsProducerDataService.getSerializedData())
                .expectNext(expected)
                .verifyComplete();
    }

    private String serialize(String... keys) {
        return Stream.of(keys)
                .map(key -> "\"" + key + "\"")
                .collect(Collectors.joining(",", "[", "]"));
    }
}