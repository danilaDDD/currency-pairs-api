package com.jfund.currencypairsservice.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfund.currencypairsservice.producer.CurrencyKeysProducer;
import com.jfund.currencypairsservice.producer.CurrencyKeysProducerData;
import com.jfund.currencypairsservice.request.GetCurrencyPairsRequest;
import com.jfund.jfundclilib.CliRunner;
import com.jfund.jfundclilib.UpdateOrCreateData;
import currencyapi.exceptions.CurrencyBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaSendCurrencyKeys implements CliRunner {
    private CurrencyKeysProducer currencyKeysProducer;
    private GetCurrencyPairsRequest getCurrencyPairsRequest;
    @Autowired
    public void setCurrencyKeysProducer(CurrencyKeysProducer currencyKeysProducer) {
        this.currencyKeysProducer = currencyKeysProducer;
    }
    @Autowired
    public void setGetCurrencyPairsRequest(GetCurrencyPairsRequest getCurrencyPairsRequest) {
        this.getCurrencyPairsRequest = getCurrencyPairsRequest;
    }

    @Override
    public UpdateOrCreateData invoke() {

        try {
            System.out.println("send currency keys value");
           List<String> currencyKeys = this.getCurrencyPairsRequest.getCurrencyPairs();
            this.currencyKeysProducer.sendCurrencyKeys(new CurrencyKeysProducerData(currencyKeys));

        } catch (CurrencyBadRequestException | ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new UpdateOrCreateData();
    }
}
