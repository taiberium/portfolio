package com.portfolio.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PositionChangeDto {

    @NotNull
    @Valid
    private InstrumentDto instrument;

    @NotNull
    private BigDecimal amount;
}
