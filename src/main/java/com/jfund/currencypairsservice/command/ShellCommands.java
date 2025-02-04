package com.jfund.currencypairsservice.command;
import com.jfund.currencypairsservice.producer.SendCurrencyPairsRunner;
import com.jfund.currencypairsservice.runner.LoadCurrencyPairsRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final LoadCurrencyPairsRunner loadCurrencyPairsRunner;
    private final SendCurrencyPairsRunner kafkaProducer;

    @ShellMethod(key = "load-currency-pairs")
    public void loadCurrencyPairs(){
        loadCurrencyPairsRunner.run();
    }

    @ShellMethod(key = "send-currency-keys-msg")
    public void sendCurrencyKeysToKafka(){
        kafkaProducer.run();
    }

}
