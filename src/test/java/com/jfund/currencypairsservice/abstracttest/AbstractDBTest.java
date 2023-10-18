package com.jfund.currencypairsservice.abstracttest;

import lombok.Getter;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

public abstract class AbstractDBTest {
    @Getter
    private final String tableName = "currency_pairs";

    private JdbcTemplate jdbcTemplate;

    public AbstractDBTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void cleanTable(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate, tableName);
    }
}
