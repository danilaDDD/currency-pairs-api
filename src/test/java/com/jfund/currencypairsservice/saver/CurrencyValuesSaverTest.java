package com.jfund.currencypairsservice.saver;

import com.jfund.currencypairsservice.abstracttest.AbstractDBTest;
import com.jfund.currencypairsservice.model.CurrencyValue;
import com.jfund.currencypairsservice.service.AsyncCurrencyValueService;
import com.jfund.jfundclilib.UpdateOrCreateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurrencyValuesSaverTest extends AbstractDBTest {
    private final CurrencyValuesSaver currencyValuesSaver;
    private final AsyncCurrencyValueService currencyValueService;

    @Autowired
    public CurrencyValuesSaverTest(JdbcTemplate jdbcTemplate, CurrencyValuesSaver currencyValuesSaver, AsyncCurrencyValueService currencyValueService) {
        super(jdbcTemplate);
        this.currencyValuesSaver = currencyValuesSaver;
        this.currencyValueService = currencyValueService;
    }

    @BeforeEach
    public void beforeEach(){
        cleanCurrencyValuesTable();
    }
    @Test
    public void testInsertNewCurrencyValues() throws ExecutionException, InterruptedException {
       UpdateOrCreateData saveData = currencyValuesSaver.saveCurrencyValues(
                Map.of("USDJPG", 16.56F, "JPGRUB", 15.45F, "USDRUB", 102.23F));

        List<CurrencyValue> currencyValues = this.currencyValueService.findAll().get();

        assertEquals(3, saveData.getCreateCount());
        assertEquals(3, currencyValues.size());

    }

    @Test
    public void testInsertDifferentByValueCurrencyValues() throws ExecutionException, InterruptedException {
        Map<String, Float> currencyData1, currencyData2;
        currencyData1 = Map.of("USDJPG", 16.56F, "JPGRUB", 15.45F, "USDRUB", 102.23F);
        currencyData2 = Map.of("USDJPG", 16.5601F, "JPGRUB", 15.452F, "USDRUB", 102.25F);

        currencyValuesSaver.saveCurrencyValues(currencyData1);
        UpdateOrCreateData lastSaveData = currencyValuesSaver.saveCurrencyValues(currencyData2);

        List<CurrencyValue> currencyValues = currencyValueService.findAll().get();

        assertEquals(2, lastSaveData.getCreateCount());
        assertEquals(5, currencyValues.size());
    }
}
