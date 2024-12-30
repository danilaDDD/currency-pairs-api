package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static com.jfund.currencypairsservice.utils.CurrencyPairsUtils.*;


@SpringBootTest
@ActiveProfiles("test")
class ReplaceCurrencyPairsTest {
    private static final List<CurrencyPair> CURRENCY_PAIR_LIST = ofKeys("EURUSD", "EURJPG", "USDJPG",
            "USDEUR", "EURAUD", "USDAUD", "AURGBP");

    @Autowired
    private ReplaceCurrencyPairService replaceCurrencyPairService;
    @Autowired
    private CurrencyPairRepository currencyPairRepository;

    @BeforeEach
    public void beforeEach(){
        currencyPairRepository.deleteAll().block();
    }

    @Test
    void testRun_WithNewCurrencyPairs_ShouldSaveAll() {
        replaceCurrencyPairService.replaceCurrencyPairs(Flux.fromIterable(CURRENCY_PAIR_LIST)).block();
       verifyByDatabase();
    }

    @Test
    void testRun_WithEmptyFlux_ThrowIllegalArgumentException() {
        StepVerifier.create(replaceCurrencyPairService.replaceCurrencyPairs(Flux.empty()))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void testRun_WithNullFlux_ThrowIllegalArgumentException() {
        StepVerifier.create(replaceCurrencyPairService.replaceCurrencyPairs(null))
                .expectError(IllegalArgumentException.class)
                .verify();
    }


    @Test
    void testRun_WithAllExistCurrencyPairs_ShouldNotReplace() {
        Flux<CurrencyPair> pairFlux = Flux.fromIterable(CURRENCY_PAIR_LIST);
        currencyPairRepository.saveAll(pairFlux).blockLast();

        replaceCurrencyPairService.replaceCurrencyPairs(pairFlux).block();

        verifyByDatabase();

    }

    @Test
    void testRun_WithPartialExistingCurrencyPairs_ShouldSaveOnlyMissing() {
        Flux<CurrencyPair> currencyPairFlux = fluxOfKeys("EURUSD", "EURJPG", "USDJPG");
        currencyPairRepository.saveAll(currencyPairFlux).blockFirst();

        Flux<CurrencyPair> newPairsFlux = fluxOfKeys("EURUSD", "EURJPG", "USDJPG", "USDEUR", "EURAUD");
        replaceCurrencyPairService.replaceCurrencyPairs(newPairsFlux).block();

        StepVerifier.create(currencyPairRepository.findAll())
                .expectNextCount(5)
                .verifyComplete();

    }

    private void verifyByDatabase(){
        StepVerifier.create(currencyPairRepository.findAll())
                .expectNextCount(CURRENCY_PAIR_LIST.size())
                .verifyComplete();
    }

}