package com.vanessa.payment_api.dto;

import java.math.BigDecimal;

import lombok.Builder;


@Builder
public record PaymentRequest (
    
    String customerId,
    BigDecimal amount,
    String currency

){}
