package com.jfund.currencypairsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "currency_pairs")
public class CurrencyPair {
    public static CurrencyPair ofCode(String code){
        String fromKey = code.substring(0, 3);
        String toKey = code.substring(3, 6);

        return new CurrencyPair(fromKey, toKey, true);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "from_key", nullable = false)
    private String fromKey;

    @Column(name = "to_key", nullable = false)
    private String toKey;

    @Column(name = "show_in_candle", nullable = false)
    private boolean showInCandle = true;

    public CurrencyPair() {}

    public CurrencyPair(String fromKey, String toKey, Boolean showInCandle) {
        this.fromKey = fromKey;
        this.toKey = toKey;
        this.showInCandle = showInCandle;
    }

    public String toString(){
        return getKey();
    }

    public String getKey(){
        return fromKey + toKey;
    }
}