package com.jfund.currencypairsservice.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurrencyPairPutRequest {
    @NotNull
    private Boolean actual;
}
