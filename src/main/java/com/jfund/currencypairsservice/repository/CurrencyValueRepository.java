package com.jfund.currencypairsservice.repository;

import com.jfund.currencypairsservice.model.CurrencyValue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface CurrencyValueRepository extends CrudRepository<CurrencyValue, Long> {
    public List<CurrencyValue> findTopByOrderByDateTimeOfActuality();
}
