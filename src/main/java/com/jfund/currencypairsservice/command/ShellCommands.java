package com.jfund.currencypairsservice.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final LoadCurrencyPairsCliRunner loadCurrencyPairsCliRunner;
    private final KafkaSendCurrencyKeys kafkaSendCurrencyKeys;

    @ShellMethod(key = "load-currency-pairs")
    public void loadCurrencyPairs(){
        loadCurrencyPairsCliRunner.invoke();
    }

    @ShellMethod(key = "send-currency-keys-msg")
    public void sendCurrencyKeysToKafka(){
        kafkaSendCurrencyKeys.invoke();
    }

}
