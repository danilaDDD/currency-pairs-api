package com.jfund.currencypairsservice.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyPairDatabaseClient {
    private final DatabaseClient databaseClient;

    public String getTableName(){
        return databaseClient
                .sql("SELECT CURRENT_DATABASE()")
                .map(((row, rowMetadata) -> row.get("current_database", String.class)))
                        .first().block();
    }

    public void deleteAll(){
        databaseClient
                .sql("DELETE FROM currency_pairs")
                .then().subscribe();
    }


}
