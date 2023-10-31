package com.jfund.currencypairsservice.saver;

import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface CurrencyValuesSaver {
    UpdateOrCreateData saveCurrencyValues(Map<String, Float> inputKeyValueMap) throws ExecutionException, InterruptedException;
}
