package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instrument {
    private String symbol;
    private InstrumentType type;

    public enum InstrumentType {
        CASH, SECURITY
    }
}
