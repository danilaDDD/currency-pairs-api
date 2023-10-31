package com.jfund.currencypairsservice.model;

import com.jfund.datautils.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Accessors(chain = true)
@Getter
@Entity
@Table(name = "currency_values")
@AllArgsConstructor
public class CurrencyValue extends AbstractEntity {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @Column(nullable = false)
    private String key;

    @Setter
    @Column(nullable = false)
    private Float value;

    @Setter
    @Column(name = "datetime_of_actuality", nullable = false)
    private LocalDateTime dateTimeOfActuality;

    @Setter
    @Column(name = "sent_to_candle_api", nullable = false)
    private Boolean sentToCandleApi = false;

    @Column(nullable = false)
    private final LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updated = LocalDateTime.now();
    public CurrencyValue(){}

    public CurrencyValue(String key, Float value, LocalDateTime dateTimeOfActuality) {
        this.key = key;
        this.value = value;
        this.dateTimeOfActuality = dateTimeOfActuality;
    }

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", dateTimeOfActuality=" + dateTimeOfActuality +
                '}';
    }

    @Override
    public void onUpdated() {
        this.updated = LocalDateTime.now();
    }
}
