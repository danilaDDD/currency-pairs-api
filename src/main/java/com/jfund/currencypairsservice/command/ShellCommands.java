package com.jfund.currencypairsservice.command;
import com.jfund.currencypairsservice.producer.CurrencyPairsRunner;
import com.jfund.currencypairsservice.runner.CurrencyPairsLoadRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final CurrencyPairsLoadRunner currencyPairsLoadRunner;
    private final CurrencyPairsRunner kafkaProducer;

    @ShellMethod(key = "load-currency-pairs")
    public void loadCurrencyPairs(){
        currencyPairsLoadRunner.run();
    }

    @ShellMethod(key = "send-currency-keys-msg")
    public void sendCurrencyKeysToKafka(){
        kafkaProducer.run();
    }

}
