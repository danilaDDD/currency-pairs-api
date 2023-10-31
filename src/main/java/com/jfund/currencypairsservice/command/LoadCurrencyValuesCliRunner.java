package com.jfund.currencypairsservice.command;

import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.request.GetCurrencyValuesFromRequest;
import com.jfund.currencypairsservice.saver.CurrencyValuesSaver;
import com.jfund.currencypairsservice.service.AsyncCurrencyPairService;
import com.jfund.currencypairsservice.service.AsyncCurrencyValueService;
import com.jfund.jfundclilib.CliRunner;
import com.jfund.jfundclilib.UpdateOrCreateData;
import currencyapi.exceptions.CurrencyBadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Slf4j(topic = "errors")
@Component
public class LoadCurrencyValuesCliRunner implements CliRunner {
    private final AsyncCurrencyPairService currencyPairService;
    private final GetCurrencyValuesFromRequest getCurrencyValuesFromRequest;
    private CurrencyValuesSaver currencyValuesSaver;
    @Autowired
    public LoadCurrencyValuesCliRunner(AsyncCurrencyPairService currencyPairService, GetCurrencyValuesFromRequest getCurrencyValuesFromRequest, CurrencyValuesSaver currencyValuesSaver) {
        this.currencyPairService = currencyPairService;
        this.getCurrencyValuesFromRequest = getCurrencyValuesFromRequest;
        this.currencyValuesSaver = currencyValuesSaver;
    }

    @Override
    public UpdateOrCreateData invoke() {
        try {
            List<String> currencyKeys = this.currencyPairService.findToCandleApi().get().stream().map(CurrencyPair::getKey).toList();
            Map<String, Float> currencyValuesMap = getCurrencyValuesFromRequest.getCurrencyValues(currencyKeys);
            return currencyValuesSaver.saveCurrencyValues(currencyValuesMap);

        } catch (InterruptedException | ExecutionException | CurrencyBadRequestException e) {
            log.error("save currency values");
            log.error(e.toString());
            return new UpdateOrCreateData().setErrorMessage(e.toString());
        }
    }
}
