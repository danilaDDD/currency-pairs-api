package com.jfund.currencypairsservice.command;

import com.jfund.jfundclilib.CliRunner;
import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {
    private final LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner;
    private final LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner;
    @Autowired
    public ShellCommands(LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner, LoadCurrencyValuesCliRunner loadCurrencyValuesCliRunner) {
        this.loadCurrencyPairsCliRunner = loadCurrencyPairsCliRunner;
        this.loadCurrencyValuesCliRunner = loadCurrencyValuesCliRunner;
    }

    @ShellMethod(key = "load-currency-pairs")
    public String loadCurrencyPairs(){
        return launchCliRunner(this.loadCurrencyPairsCliRunner);
    }

    @ShellMethod(key = "load-currency-values")
    public String loadCurrencyValues(){
        return launchCliRunner(loadCurrencyValuesCliRunner);
    }

    private String launchCliRunner(CliRunner cliRunner){
        UpdateOrCreateData updateOrCreateData = cliRunner.invoke();
        return updateOrCreateData.toString();
    }
}
