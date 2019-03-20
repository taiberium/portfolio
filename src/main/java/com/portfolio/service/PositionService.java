package com.portfolio.service;

import com.portfolio.model.Instrument;
import com.portfolio.model.Position;
import com.portfolio.model.PositionChange;
import com.portfolio.model.Transaction;
import com.portfolio.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final TransactionRepository transactionRepository;

    public Flux<Position> get(String account) {
        return transactionRepository.findAllByAccount(account)
                .flatMap(this::map)
                .collect(Collectors.toMap(PositionChange::getInstrument, PositionChange::getAmount, BigDecimal::add))
                .flatMapMany((Map<Instrument, BigDecimal> map) -> Flux.fromIterable(map.entrySet()))
                .map((Map.Entry<Instrument, BigDecimal> entry) -> new Position(entry.getKey(), entry.getValue()));
    }

    private Flux<PositionChange> map(Transaction transaction) {
        return Flux.fromStream(
                Stream.of(transaction.getFirstSide(), transaction.getSecondSide())
                        .filter(Objects::nonNull)
        );
    }
}
