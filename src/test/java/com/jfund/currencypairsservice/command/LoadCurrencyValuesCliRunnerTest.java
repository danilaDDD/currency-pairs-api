package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.model.CurrencyValue;
import com.jfund.currencypairsservice.service.AsyncCurrencyPairService;
import com.jfund.currencypairsservice.service.AsyncCurrencyValueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoadCurrencyValuesCliRunnerTest extends AbstractDBTest {
    private final LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner;
    private final AsyncCurrencyValueService asyncCurrencyValueService;
    private final AsyncCurrencyPairService asyncCurrencyPairService;

    @Autowired
    public LoadCurrencyValuesCliRunnerTest(JdbcTemplate jdbcTemplate, LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner, AsyncCurrencyValueService asyncCurrencyValueService, AsyncCurrencyPairService asyncCurrencyPairService) {
        super(jdbcTemplate);
        this.loadCurrencyValuesCliRunner = loadCurrencyValuesCliRunner;
        this.asyncCurrencyValueService = asyncCurrencyValueService;
        this.asyncCurrencyPairService = asyncCurrencyPairService;
    }

    @BeforeEach
    public void beforeEach(){
        cleanCurrencyValuesTable();
        cleanCurrencyPairsTable();
    }
    @Test
    public void testLoadCurrencyValues() throws ExecutionException, InterruptedException {
        asyncCurrencyPairService.insert(List.of(
                new CurrencyPair("USD", "RUB", true),
                new CurrencyPair("JPG", "RUB", true),
                new CurrencyPair("GBP", "RUB", false)
        )).get();

        loadCurrencyValuesCliRunner.invoke();

        assertTrue(asyncCurrencyValueService.count().get() > 0);
    }
}
