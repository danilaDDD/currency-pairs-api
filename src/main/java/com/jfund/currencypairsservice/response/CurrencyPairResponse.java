package com.jfund.currencypairsservice.response;

import com.jfund.currencypairsservice.model.CurrencyPair;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@ToString
@RequiredArgsConstructor
@Schema(description = "Валютная пара")
public class CurrencyPairResponse {
    public static CurrencyPairResponse of(CurrencyPair pair){
        return new CurrencyPairResponse(pair.getId(),
                pair.getFromKey(), pair.getToKey(), pair.isShowInCandle());
    }

    private final Long id;
    private final String fromCurrency;
    private final String toCurrency;
    private final Boolean active;
}
