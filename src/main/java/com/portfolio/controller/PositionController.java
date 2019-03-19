package com.portfolio.controller;

import com.portfolio.model.dto.PositionDto;
import com.portfolio.repository.QuoteRepository;
import com.portfolio.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("portfolio")
public class PositionController {

    private final PositionService positionService;
    private final QuoteRepository quoteRepository;

    @GetMapping("{account}")
    public Flux<PositionDto> get(@PathVariable("account") String account) {
       return positionService.get(account)
                .flatMap(position ->
                        quoteRepository.get(position.getSymbol()).map(quote -> new PositionDto(position, quote))
                );
    }
}
