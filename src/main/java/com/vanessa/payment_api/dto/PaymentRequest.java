package com.vanessa.payment_api.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record PaymentRequest (

    @Schema(example = "PAY-111")
    String customerId,

    @Schema(example = "5000")
    BigDecimal amount,

    @Schema(example = "PHP")
    String currency

){}
