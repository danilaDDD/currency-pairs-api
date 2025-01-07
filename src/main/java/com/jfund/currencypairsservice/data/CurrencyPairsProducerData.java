package com.jfund.currencypairsservice.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@ToString
public class CurrencyPairsProducerData {
    private final String serializedData;
    private final boolean empty;
    private final LocalDateTime createdAt;

    public CurrencyPairsProducerData(String serializedData) {
        this(serializedData, false, LocalDateTime.now());
    }

    public CurrencyPairsProducerData(String serializedData, boolean empty) {
        this(serializedData, empty, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CurrencyPairsProducerData that = (CurrencyPairsProducerData) object;
        return empty == that.empty && Objects.equals(serializedData, that.serializedData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serializedData, empty);
    }
}
