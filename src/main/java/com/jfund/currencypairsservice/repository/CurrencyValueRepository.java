package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.model.CurrencyValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyValueRepository extends CrudRepository<CurrencyValue, Long> {
    @Query(value = """
    SELECT v FROM CurrencyValue v
    WHERE v.dateTimeOfActuality = (SELECT v2.dateTimeOfActuality FROM CurrencyValue v2 ORDER BY v2.dateTimeOfActuality DESC LIMIT 1)
    """)
    public List<CurrencyValue> findLastByOrderByDateTimeOfActuality();
}
