package com.jfund.currencypairsservice.abstracttest;

import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@DataMongoTest
public abstract class AbstractDBTest {
    @Getter
    private final MongoTemplate mongoTemplate;

    @Getter
    String collectionName = "currencyPairs";
    @Autowired
    public AbstractDBTest(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @BeforeEach
    public void beforeEach(){
        mongoTemplate.remove(new Query(), collectionName);
    }
}
