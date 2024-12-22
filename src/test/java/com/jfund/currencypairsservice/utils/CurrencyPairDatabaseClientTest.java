package com.jfund.currencypairsservice.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CurrencyPairDatabaseClientTest {
    @Autowired
    private CurrencyPairDatabaseClient databaseClient;

    @Test
    void getTableName() {
        System.out.println(databaseClient.getTableName());
    }
}