package com.portfolio.model;

import com.portfolio.model.Position;
import com.portfolio.model.Quote;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PortfolioPosition {

    private final Position position;
    private final Quote quote;

}
