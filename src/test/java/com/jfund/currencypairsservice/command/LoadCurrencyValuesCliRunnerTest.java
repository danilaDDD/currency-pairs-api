package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.service.AsyncCurrencyPairService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class LoadCurrencyValuesCliRunnerTest extends AbstractDBTest {
    private final AsyncCurrencyPairService asyncCurrencyPairService;
    @BeforeEach
    public void beforeEach(){
        cleanCurrencyPairsTable();
    }

    @Autowired
    public LoadCurrencyValuesCliRunnerTest(JdbcTemplate jdbcTemplate, AsyncCurrencyPairService asyncCurrencyPairService) {
        super(jdbcTemplate);
        this.asyncCurrencyPairService = asyncCurrencyPairService;
    }
}
