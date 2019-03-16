package com.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PositionChange {

    private String symbol;
    private BigDecimal amount;
}
