package com.jfund.currencypairsservice.command;

import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellCommands {
    private final LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner;
    @Autowired
    public ShellCommands(LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner) {
        this.loadCurrencyPairsCliRunner = loadCurrencyPairsCliRunner;
    }

    @ShellMethod(key = "load-currency-pairs")
    public String loadCurrencyPairs(){
        UpdateOrCreateData updateOrCreateData = loadCurrencyPairsCliRunner.invoke();
        return updateOrCreateData.toString();
    }
}
