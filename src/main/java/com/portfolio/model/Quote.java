package com.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class Quote {

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String sector;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal latestPrice;
    private BigDecimal marketCap;
    private BigDecimal peRatio;
    private BigDecimal week52High;
    private BigDecimal week52Low;
    private BigDecimal ytdChange;

}
