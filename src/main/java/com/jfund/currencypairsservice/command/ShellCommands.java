package com.jfund.currencypairsservice.command;

import com.jfund.jfundclilib.UpdateOrCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class ShellCommands {
    @Autowired
    private LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner;

    @ShellMethod(key = "load-currency-pairs")
    public String loadCurrencyPairs(){
        UpdateOrCreateData updateOrCreateData = loadCurrencyPairsCliRunner.invoke();
        return updateOrCreateData.toString();
    }
}
