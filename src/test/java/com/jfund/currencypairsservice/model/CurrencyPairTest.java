package com.jfund.currencypairsservice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyPairTest {

    @Test
    public void createEntityTest(){
        CurrencyPair pair = new CurrencyPair("USD", "JPG", false);

        Assertions.assertEquals(pair.getKey(), "USDJPG");
        Assertions.assertEquals(pair.getId(), "USDJPG");
    }
}
