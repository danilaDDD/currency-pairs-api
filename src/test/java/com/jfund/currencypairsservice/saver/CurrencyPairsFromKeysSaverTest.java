package com.jfund.currencypairsservice.saver;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.service.AsyncCurrencyPairService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringBootTest
public class CurrencyPairsFromKeysSaverTest extends AbstractDBTest {
    private final CurrencyPairsFromKeysSaver currencyPairsFromKeysSaver;
    private final AsyncCurrencyPairService currencyPairService;
    @BeforeEach
    public void beforeEach(){
        cleanCurrencyPairsTable();
    }

    @Autowired
    public CurrencyPairsFromKeysSaverTest(JdbcTemplate jdbcTemplate,
                                          CurrencyPairsFromKeysSaver currencyPairsFromKeysSaver,
                                          AsyncCurrencyPairService asyncCurrencyPairService){
        super(jdbcTemplate);
        this.currencyPairsFromKeysSaver = currencyPairsFromKeysSaver;
        this.currencyPairService = asyncCurrencyPairService;
    }

    @Test
    @Transactional
    public void saveCurrencyPairsFromKeysTest() throws ExecutionException, InterruptedException {
        List<String> currencyKeys = List.of("USDGBP", "USDRUB", "RUBGBP", "USDJPA");
        this.currencyPairsFromKeysSaver.save(currencyKeys);

        Set<String> currencyKeysFromDB = this.currencyPairService.findAll().get()
                .stream().map(CurrencyPair::getKey)
                .collect(Collectors.toSet());

        Assertions.assertTrue(currencyKeysFromDB.containsAll(currencyKeys));

    }

}
