package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    private String id;
    private String account;
    private Type type;
    private PositionChange input;
    private PositionChange output;


   public enum Type {
        BUY, SELL, INPUT, OUTPUT
    }
}
