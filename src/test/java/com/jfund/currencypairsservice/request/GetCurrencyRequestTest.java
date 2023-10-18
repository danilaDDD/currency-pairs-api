package com.jfund.currencypairsservice.request;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.saver.CurrencyPairsFromKeysSaver;
import currencyapi.exceptions.CurrencyBadRequestException;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class GetCurrencyRequestTest {
    @Autowired
    private GetCurrencyPairsRequest getCurrencyPairsRequest;

    @Autowired
    public GetCurrencyRequestTest(GetCurrencyPairsRequest getCurrencyPairsRequest){
        this.getCurrencyPairsRequest = getCurrencyPairsRequest;
    }

    @Test
    public void getCurrencyPairsFromApiTest() throws CurrencyBadRequestException, ExecutionException, InterruptedException {
        List<String> pairs = this.getCurrencyPairsRequest.getCurrencyPairs();
        Assertions.assertNotEquals(0, pairs.size());
    }
}
