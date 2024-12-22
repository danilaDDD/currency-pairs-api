package com.jfund.currencypairsservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CurrencyPairServiceSaveCurrentAndDeleteOtherTest {
    @Autowired
    private CurrencyPairService currencyPairService;

    @Autowired
    private DatabaseClient databaseClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveCurrentAndDeleteOther() {
    }
}