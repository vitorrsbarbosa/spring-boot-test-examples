package com.webmvc.payment.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PaymentRequest {
    @NotNull
    private String creditCardNumber;
}
