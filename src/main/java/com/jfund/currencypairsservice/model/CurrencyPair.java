package com.jfund.currencypairsservice.model;

import com.jfund.datautils.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "currencyPairs")
public class CurrencyPair extends AbstractEntity {
    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    private String fromKey;

    @Getter
    @Setter
    private String toKey;

    @Getter
    @Indexed
    private String key;

    @Getter
    @Setter
    private Boolean showInCandle = true;

    public CurrencyPair() {}

    public CurrencyPair(String fromKey, String toKey, Boolean showInCandle) {
        this.fromKey = fromKey;
        this.toKey = toKey;
        this.key = fromKey + toKey;
        this.showInCandle = showInCandle;
        this.id = this.key;
    }

    public String toString(){
        return key;
    }
}