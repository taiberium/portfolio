package com.portfolio.service;

import com.portfolio.model.PortfolioPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PositionService positionService;
    private final QuoteService quoteService;

    public Flux<PortfolioPosition> get(String account) {
        return positionService.get(account)
                .flatMap(position ->
                        quoteService.get(position.getInstrument())
                                .map(quote -> new PortfolioPosition(position, quote))
                                .defaultIfEmpty(new PortfolioPosition(position, null))
                );
    }
}
