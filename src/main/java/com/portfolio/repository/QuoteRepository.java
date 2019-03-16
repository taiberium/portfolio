package com.portfolio.repository;

import com.portfolio.model.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Repository
@RequiredArgsConstructor
public class QuoteRepository {

    private final WebClient webClient;

    public Mono<Quote> get(String symbol) {
        return webClient.get()
                .uri("https://api.iextrading.com/1.0/stock/{symbol}/quote", symbol)
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Quote.class));
    }
}
