package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.model.CurrencyPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

@DataMongoTest
public class CurrencyPairRepositoryTest extends AbstractDBTest {
    private final CurrencyPairRepository repository;

    @Autowired
    public CurrencyPairRepositoryTest(MongoTemplate mongoTemplate, CurrencyPairRepository repository){
        super(mongoTemplate);
        this.repository = repository;
    }

    @Test
    public void insertTest() throws ExecutionException, InterruptedException {
        List<CurrencyPair> currencyList = List.of(
                new CurrencyPair("USD", "JPG", false),
                new CurrencyPair("USD", "GBP", true),
                new CurrencyPair()
        );

        this.repository.insert(currencyList);
        long currencyCount = this.repository.count();

        Assertions.assertEquals(currencyCount, 3);
    }
}
