package com.portfolio.controller;

import com.portfolio.model.PortfolioPosition;
import com.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("portfolio")
public class PortfolioController {

    private final PortfolioService service;

    @GetMapping("{account}")
    public Flux<PortfolioPosition> get(@PathVariable("account") String account) {
        return service.get(account);
    }
}
