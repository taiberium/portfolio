package com.portfolio.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PositionChangeDto {

    @NotEmpty
    private String symbol;
    @NotNull
    private BigDecimal amount;
}
