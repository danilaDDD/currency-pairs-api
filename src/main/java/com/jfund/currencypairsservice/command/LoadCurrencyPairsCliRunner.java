package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import com.jfund.currencypairsservice.saver.CurrencyPairsFromKeysSaver;
import com.jfund.jfundclilib.CliRunner;
import com.jfund.jfundclilib.UpdateOrCreateData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j(topic = "errors")
@Component
public class LoadCurrencyPairsCliRunner implements CliRunner {
    private final CurrencyPairsFromKeysSaver currencyPairsFromKeysSaver;
    private final GetCurrencyPairsRequest getCurrencyPairsRequest;

    @Autowired
    public LoadCurrencyPairsCliRunner(CurrencyPairsFromKeysSaver currencyPairsFromKeysSaver, GetCurrencyPairsRequest getCurrencyPairsRequest){
        this.currencyPairsFromKeysSaver = currencyPairsFromKeysSaver;
        this.getCurrencyPairsRequest = getCurrencyPairsRequest;
    }

    @Override
    public UpdateOrCreateData invoke() {
        try {
            List<String> currencyPairsFromApi = getCurrencyPairsRequest.getCurrencyPairs();
            return this.currencyPairsFromKeysSaver.save(currencyPairsFromApi);
        } catch (Exception e) {
            log.error("load current pairs errors");
            log.error(e.toString());
            return new UpdateOrCreateData(e.toString());
        }

    }
}
