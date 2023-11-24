package com.jfund.currencypairsservice.saver;

import com.jfund.currencypairsservice.exceptions.SaveCurrencyKeysRuntimeException;
import com.jfund.currencypairsservice.model.CurrencyPair;
import com.jfund.currencypairsservice.service.AsyncCurrencyPairService;
import com.jfund.jfundclilib.UpdateOrCreateData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class SimpleCurrencyPairsFromKeysSaver implements CurrencyPairsFromKeysSaver {
    private AsyncCurrencyPairService currencyPairService;

    @Autowired
    public void setCurrencyPairService(AsyncCurrencyPairService currencyPairService){
        this.currencyPairService = currencyPairService;
    }

    @Transactional
    @Override
    public UpdateOrCreateData save(List<String> actualCurrencyPairsNames){
        try {
            List<CurrencyPair> pairsObjectsFromDB = currencyPairService.findAll().get();
            Set<String> currencyPairsNamesFromDB = pairsObjectsFromDB
                    .stream().map(CurrencyPair::getKey)
                    .collect(Collectors.toSet());

            Set<String> currencyPairsToCreate = new HashSet<>(actualCurrencyPairsNames);
            currencyPairsToCreate.removeAll(currencyPairsNamesFromDB);

            Set<String> currencyPairsToDelete = new HashSet<>(currencyPairsNamesFromDB);
            actualCurrencyPairsNames.forEach(currencyPairsToDelete::remove);

            List<CurrencyPair> pairsObjectToCreate = createCurrencyPairObjectsFromKeys(currencyPairsToCreate);

            int createdCount = currencyPairService.insert(pairsObjectToCreate).get().size();

            int deletedCount = 0;
            if (!currencyPairsToDelete.isEmpty()) {
                currencyPairService.deleteByKeys(currencyPairsToDelete).get();
                deletedCount = currencyPairsToDelete.size();
            }

            return new UpdateOrCreateData(0, createdCount, deletedCount, null);
        } catch (ExecutionException | InterruptedException e) {
            throw new SaveCurrencyKeysRuntimeException(e);
        }
    }
    private List<CurrencyPair> createCurrencyPairObjectsFromKeys(Set<String> currencyPairKeys){
        return currencyPairKeys
                .stream().map(pairStr -> {
                    String[] charsArray = pairStr.split("");
                    String currencyFrom = String.join("", Arrays.copyOfRange(charsArray, 0, 3));
                    String currencyTo = String.join("", Arrays.copyOfRange(charsArray, 3, 6));

                    return new CurrencyPair(currencyFrom, currencyTo, true);
                }).toList();
    }
}
