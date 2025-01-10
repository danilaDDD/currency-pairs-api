package com.jfund.currencypairsservice.controller;

import com.jfund.currencypairsservice.request.CurrencyPairPutRequest;
import com.jfund.currencypairsservice.response.CurrencyPairResponse;
import com.jfund.currencypairsservice.service.CurrencyPairCRUDService;
import com.jfund.currencypairsservice.service.CurrencyPairRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "API валютных пар")
@RestController
@RequestMapping("/currency/pairs")
@RequiredArgsConstructor
public class CurrencyPairController {
    private final CurrencyPairRestService service;

    @GetMapping
    @Operation(description = "Получение всех валютных пар")
    public Flux<CurrencyPairResponse> getAll(){
        return service.getAll()
                .doOnError(e -> log.error(e.getMessage()));
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение валютной пары по идентификатору")
    public ResponseEntity<CurrencyPairResponse> getById(@PathVariable Long id){
        CurrencyPairResponse pair = service.getById(id).block();
        return pair == null? ResponseEntity.badRequest().build() :ResponseEntity.ok(pair);
    }

    @PutMapping("/{id}")
    @Operation(description = "Обновление валютной пары")
    public ResponseEntity<CurrencyPairResponse> update(@Valid @RequestBody CurrencyPairPutRequest request,
                                             @PathVariable Long id){
        CurrencyPairResponse pair = service.put(request, id).block();
        return pair == null? ResponseEntity.badRequest().build() :ResponseEntity.ok(pair);
    }
}
