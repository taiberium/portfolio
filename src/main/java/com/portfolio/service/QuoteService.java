package com.portfolio.service;

import com.portfolio.model.Instrument;
import com.portfolio.model.Quote;
import com.portfolio.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository repository;

    public Mono<Quote> get(Instrument instrument) {
        if (instrument.getType() == Instrument.InstrumentType.CASH){
            //not implemented yet
            return Mono.empty();
        }

        return repository.get(instrument.getSymbol());
    }

}
