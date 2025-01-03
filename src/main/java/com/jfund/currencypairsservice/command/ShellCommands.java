package com.jfund.currencypairsservice.command;
import com.jfund.currencypairsservice.producer.CurrencyPairsProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final CurrencyPairsLoader currencyPairsLoader;
    private final CurrencyPairsProducer kafkaProducer;

    @ShellMethod(key = "load-currency-pairs")
    public void loadCurrencyPairs(){
        currencyPairsLoader.invoke();
    }

    @ShellMethod(key = "send-currency-keys-msg")
    public void sendCurrencyKeysToKafka(){
        kafkaProducer.sendCurrencyKeys();
    }

}
