package com.jfund.currencypairsservice.command;

import com.jfund.jfundclilib.CliRunner;
import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {
    private final CliRunner loadCurrencyPairsCliRunner;
    private final CliRunner loadCurrencyValuesCliRunner;
    private final CliRunner kafkaSendCurrencyKeys;

    @Autowired
    public ShellCommands(LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner, LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner, KafkaSendCurrencyKeys kafkaSendCurrencyKeys) {
        this.loadCurrencyPairsCliRunner = loadCurrencyPairsCliRunner;
        this.loadCurrencyValuesCliRunner = loadCurrencyValuesCliRunner;
        this.kafkaSendCurrencyKeys = kafkaSendCurrencyKeys;
    }

    @ShellMethod(key = "load-currency-pairs")
    public String loadCurrencyPairs(){
        return launchCliRunner(this.loadCurrencyPairsCliRunner);
    }

    @ShellMethod(key = "load-currency-values")
    public String loadCurrencyValues(){
        return launchCliRunner(loadCurrencyValuesCliRunner);
    }

    @ShellMethod(key = "send-currency-keys-msg")
    public String sendCurrencyKeysToKafka(){
        return launchCliRunner(kafkaSendCurrencyKeys);
    }

    private String launchCliRunner(CliRunner cliRunner){
        UpdateOrCreateData updateOrCreateData = cliRunner.invoke();
        return updateOrCreateData.toString();
    }
}
