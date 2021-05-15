package com.barbosa.datajpa.receipt.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Receipt {
    private final LocalDateTime date;
    private final String creditCardNumber;
    private final BigDecimal amount;
}
