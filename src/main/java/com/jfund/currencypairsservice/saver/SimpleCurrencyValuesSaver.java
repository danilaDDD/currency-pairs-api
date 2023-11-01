package com.jfund.currencypairsservice.saver;

import com.jfund.currencypairsservice.model.CurrencyValue;
import com.jfund.currencypairsservice.service.AsyncCurrencyValueService;
import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SimpleCurrencyValuesSaver implements CurrencyValuesSaver {
    @Autowired
    private AsyncCurrencyValueService currencyValueService;
    private final float accuracy = 0.0001F;
    private final LocalDateTime runDateTime = LocalDateTime.now();

    public SimpleCurrencyValuesSaver(){}
    @Autowired
    public void setCurrencyValueService(AsyncCurrencyValueService currencyValueService) {
        this.currencyValueService = currencyValueService;
    }
    @Override
    public UpdateOrCreateData saveCurrencyValues(Map<String, Float> inputKeyValueMap) throws ExecutionException, InterruptedException {
        if(!inputKeyValueMap.isEmpty()) {
            return handleSaveProcess(inputKeyValueMap);
        }

        return new UpdateOrCreateData(0, 0, 0, "input data is empty");
    }

    private UpdateOrCreateData handleSaveProcess(Map<String, Float> inputKeyValueMap) throws ExecutionException, InterruptedException {
        Map<String, CurrencyValue> lastCurrencyValueKeyToEntity = currencyValueService.findLastByDateTimeOfActuality().get()
                .stream()
                .collect(Collectors.toMap(CurrencyValue::getKey, Function.identity()));

        if (lastCurrencyValueKeyToEntity.isEmpty()){
            return insertAll(inputKeyValueMap);
        }

        List<CurrencyValue> toInsertCurrencyValues = getChangedByValueNewValues(inputKeyValueMap, lastCurrencyValueKeyToEntity);
        if (!toInsertCurrencyValues.isEmpty()) {
            currencyValueService.insert(toInsertCurrencyValues).get();
            return new UpdateOrCreateData().setCreateCount(toInsertCurrencyValues.size());
        }

        return new UpdateOrCreateData();
    }

    private UpdateOrCreateData insertAll(Map<String, Float> inputKeyValueMap) throws ExecutionException, InterruptedException {
        List<CurrencyValue> currencyValues = inputKeyValueMap.entrySet().stream()
                .map(entryKeyValue -> new CurrencyValue(entryKeyValue.getKey(), entryKeyValue.getValue(), this.runDateTime)).toList();
        if(!currencyValues.isEmpty())
            this.currencyValueService.insert(currencyValues).get();

        return new UpdateOrCreateData().setCreateCount(currencyValues.size());
    }

    private List<CurrencyValue> getChangedByValueNewValues(Map<String, Float> inputKeyValueMap, Map<String, CurrencyValue> lastCurrencyValueKeyToEntity) throws ExecutionException, InterruptedException {

        return inputKeyValueMap.entrySet().stream()
                .filter(keyValueEntry -> {
                        String currencyKey = keyValueEntry.getKey();
                        CurrencyValue dbCurrencyValue = lastCurrencyValueKeyToEntity.getOrDefault(currencyKey, null);
                        if(dbCurrencyValue != null){
                            float inputValue = keyValueEntry.getValue();
                            return isDifferentValue(dbCurrencyValue, inputValue);
                        }

                        return true;
        }).map(entryKeyValue -> new CurrencyValue(entryKeyValue.getKey(), entryKeyValue.getValue(), this.runDateTime)).toList();
    }

    private boolean isDifferentValue(CurrencyValue currencyValue, float value){
        return Math.abs(value - currencyValue.getValue()) > this.accuracy;
    }
}