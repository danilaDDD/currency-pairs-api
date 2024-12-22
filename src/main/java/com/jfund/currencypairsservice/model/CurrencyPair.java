package com.jfund.currencypairsservice.model;

import com.jfund.commonlib.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table(name = "currency_pairs")
@AllArgsConstructor
public class CurrencyPair {
    public static CurrencyPair ofCode(String code){
        String fromKey = code.substring(0, 3);
        String toKey = code.substring(3, 6);

        return new CurrencyPair(fromKey, toKey, true);
    }

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_key", nullable = false)
    private String fromKey;

    @Column(name = "to_key", nullable = false)
    private String toKey;

    @Setter
    @Column(name = "show_in_candle", nullable = false)
    private Boolean showInCandle = true;

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