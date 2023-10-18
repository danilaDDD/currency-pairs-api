package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.model.CurrencyPair;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringBootTest
public class CurrencyPairRepositoryTest extends AbstractDBTest {
    private final CurrencyPairRepository repository;

    @BeforeEach
    public void beforeEach(){
        cleanTable();
    }

    @Autowired
    public CurrencyPairRepositoryTest(JdbcTemplate jdbcTemplate, CurrencyPairRepository repository){
        super(jdbcTemplate);
        this.repository = repository;
    }

    @Test
    public void insertTest() throws ExecutionException, InterruptedException {
        List<CurrencyPair> currencyList = insertSimpleCurrencyPairs();

        this.repository.saveAll(currencyList);

        Assertions.assertEquals(currencyList.size(), this.repository.count());
    }

    @Test
    public void insertOrUpdateTest(){
        insertSimpleCurrencyPairs();

        List<CurrencyPair> pairs = (List<CurrencyPair>) this.repository.findAll();

        for(CurrencyPair pair : pairs){
            pair.setToKey(pair.getKey() + "1");
        }

        this.repository.saveAll(pairs);

        List<CurrencyPair> updatedPairs = (List<CurrencyPair>) this.repository.findAll();

        Long updatedPairsCount = updatedPairs.stream().filter(currencyPair -> currencyPair.getKey().endsWith("1")).count();

        Assertions.assertEquals(updatedPairs.size(), updatedPairsCount);
    }

    @Test
    @Transactional
    public void testDeleteByKeys(){
        List<CurrencyPair> insertedCurrencyPairs = insertSimpleCurrencyPairs();
        Set<String> currencyKeys = insertedCurrencyPairs.stream()
                .map(CurrencyPair::getKey).collect(Collectors.toSet());

        this.repository.deleteCurrencyPairByKeyIn(currencyKeys);

        Assertions.assertEquals(0, this.repository.count());
    }

    private List<CurrencyPair> insertSimpleCurrencyPairs(){
        List<CurrencyPair> currencyList = List.of(
                new CurrencyPair("USD", "JPG", false),
                new CurrencyPair("USD", "GBP", true)
        );

        this.repository.saveAll(currencyList);

        return currencyList;
    }
}
