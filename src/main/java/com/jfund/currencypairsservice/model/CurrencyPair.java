package com.jfund.currencypairsservice.model;

import com.jfund.datautils.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "currency_pairs")
@AllArgsConstructor
public class CurrencyPair extends AbstractEntity{
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Getter
    @Column(name = "from_key", nullable = false)
    private String fromKey;

    @Getter
    @Column(name = "to_key", nullable = false)
    private String toKey;

    @Getter
    @Setter
    @Column(nullable = false)
    private String key;
    private Boolean showInCandle = true;

    @Getter
    @Column(nullable = false)
    private final LocalDateTime created = LocalDateTime.now();

    @Getter
    @Column(nullable = false)
    private LocalDateTime updated = LocalDateTime.now();

    public CurrencyPair() {}

    public CurrencyPair(String fromKey, String toKey, Boolean showInCandle) {
        this.fromKey = fromKey;
        this.toKey = toKey;
       updateKey();
        this.showInCandle = showInCandle;
    }

    public void updateKey(){
        this.key = getFromKey() + getToKey();
    }

    public String toString(){
        return key;
    }

    public void setFromKey(String fromKey) {
        this.fromKey = fromKey;
        updateKey();
    }

    public void setToKey(String toKey) {
        this.toKey = toKey;
        updateKey();
    }

    @Override
    public void onUpdated() {
        this.updated = LocalDateTime.now();
    }
}