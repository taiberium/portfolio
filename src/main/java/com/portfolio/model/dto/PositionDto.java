package com.portfolio.model.dto;

import com.portfolio.model.Position;
import com.portfolio.model.Quote;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PositionDto {

    private final Position position;
    private final Quote quote;

}
