package com.portfolio.service;

import com.portfolio.model.Position;
import com.portfolio.model.PositionChange;
import com.portfolio.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final TransactionRepository transactionRepository;

    public Flux<Position> get(String account) {
        return transactionRepository.findAllByAccount(account)
                .flatMap(transaction -> Flux.just(transaction.getInput(), transaction.getOutput()))
                .collect(Collectors.toMap(PositionChange::getSymbol, PositionChange::getAmount, BigDecimal::add))
                .flatMapMany((Map<String, BigDecimal> map) -> Flux.fromIterable(map.entrySet()))
                .map((Map.Entry<String, BigDecimal> entry) -> new Position(entry.getKey(), entry.getValue()));
    }
}
