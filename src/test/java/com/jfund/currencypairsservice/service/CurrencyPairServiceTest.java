package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.model.CurrencyPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class CurrencyPairServiceTest extends AbstractDBTest {
    private final AsyncCurrencyPairService asyncCurrencyPairService;

    @BeforeEach
    public void beforeEach(){
        cleanCurrencyPairsTable();
    }

    @Autowired
    public CurrencyPairServiceTest(JdbcTemplate jdbcTemplate, AsyncCurrencyPairService service){
        super(jdbcTemplate);
        this.asyncCurrencyPairService = service;
    }

    @Test
    public void insertTest() throws ExecutionException, InterruptedException {
        List<CurrencyPair> currencyList = List.of(
                new CurrencyPair("USD", "JPG", true),
                new CurrencyPair("USD", "GBP", false)
        );

        this.asyncCurrencyPairService.insert(currencyList).get();
        int currencyCount = this.asyncCurrencyPairService.findAll().get().size();

        Assertions.assertEquals(currencyCount, 2);
    }

    @Test
    public void runAsyncInsertAndUpdateTest() throws ExecutionException, InterruptedException {
        List<CurrencyPair> toCreateCurrencyPairList = List.of(
                new CurrencyPair("USD", "JPG", true),
                new CurrencyPair("USD", "GBP", false)
        );

        asyncCurrencyPairService.insert(toCreateCurrencyPairList).get();

        asyncCurrencyPairService.findAll()
                .thenApply(entityList -> {
                    entityList.forEach(currencyPair -> {
                        currencyPair.setToKey(currencyPair.getToKey() + "1");
                    });
                    return entityList;
                }).thenAccept(asyncCurrencyPairService::update).get();

        long changedCount = asyncCurrencyPairService.findAll().get()
                .stream()
                .filter(currencyPair -> currencyPair.getKey().endsWith("1"))
                .count();

        Assertions.assertEquals(2, changedCount);
    }

}
