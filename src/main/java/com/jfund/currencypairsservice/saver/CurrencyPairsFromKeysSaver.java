package com.jfund.currencypairsservice.saver;

import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public interface CurrencyPairsFromKeysSaver {
    UpdateOrCreateData save(List<String> currencyKeys) throws ExecutionException, InterruptedException;
}
