package com.portfolio.controller;

import com.portfolio.model.Transaction;
import com.portfolio.model.dto.TransactionDto;
import com.portfolio.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private final ModelMapper mapper;
    private final TransactionRepository repository;

    @GetMapping
    public Flux<Transaction> get(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "type", required = false) Transaction.Type type
    ) {
        Transaction example = Transaction.builder()
                .id(id)
                .account(account)
                .type(type)
                .build();
        return repository.findAll(Example.of(example));
    }

    @PostMapping
    public Mono<Transaction> save(@RequestBody @Valid TransactionDto transactionDto) {
        Transaction transaction = mapper.map(transactionDto, Transaction.class);
        return repository.save(transaction);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }
}
