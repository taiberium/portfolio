package com.portfolio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private String id;
    @NotEmpty
    private String account;
    @NotNull
    private TypeDto type;
    @NotNull
    @Valid
    private PositionChangeDto firstSide;
    @NotNull
    @Valid
    private PositionChangeDto secondSide;


    public enum TypeDto {
        BUY, SELL, INPUT, OUTPUT
    }
}
