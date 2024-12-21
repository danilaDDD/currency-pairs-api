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

@Table(name = "currency_pairs")
@AllArgsConstructor
public class CurrencyPair extends AbstractEntity {
    public static CurrencyPair ofCode(String code){
        String fromKey = code.substring(0, 3);
        String toKey = code.substring(3, 6);

        return new CurrencyPair(fromKey, toKey, true);
    }
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

    public void setToKey(String toKey) {
        this.toKey = toKey;
        updateKey();
    }

    public String getKey(){
        return fromKey + toKey;
    }

    @Override
    public void onUpdated() {
        this.updated = LocalDateTime.now();
    }
}