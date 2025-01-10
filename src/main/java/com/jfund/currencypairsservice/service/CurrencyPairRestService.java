package com.jfund.currencypairsservice.service;

import com.jfund.currencypairsservice.repository.CurrencyPairRepository;
import com.jfund.currencypairsservice.request.CurrencyPairPutRequest;
import com.jfund.currencypairsservice.response.CurrencyPairResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyPairRestService {
    private final CurrencyPairRepository repository;

    public Flux<CurrencyPairResponse> getAll(){
        return repository.findAll()
                .map(CurrencyPairResponse::of);
    }

    public Mono<CurrencyPairResponse> put(CurrencyPairPutRequest request, Long id){
        return repository.findById(id).flatMap(pair -> {
            pair.setShowInCandle(request.getActual());
            return repository.save(pair);
        }).map(CurrencyPairResponse::of);

    }

    public Mono<CurrencyPairResponse> getById(Long id) {
        return repository.findById(id).map(CurrencyPairResponse::of);

    }
}
