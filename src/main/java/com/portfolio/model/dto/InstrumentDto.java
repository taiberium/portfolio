package com.portfolio.model.dto;

import com.portfolio.model.Instrument;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class InstrumentDto {
    @NotEmpty
    private String symbol;
    @NotNull
    private Instrument.InstrumentType type;

    public enum InstrumentType {
        CASH, SECURITY
    }
}
