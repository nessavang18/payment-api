package com.vanessa.payment_api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    
    @Schema(example = "1")
    private Long paymentId;

    @Schema(example = "PAY-123")
    private String paymentreference;

    @Schema(example = "CUST-001")
    private String customerId ;

    @Schema(example = "5000")
    private BigDecimal amount;

    @Schema(example = "PHP")
    private String currency;

    @Schema(example = "PENDING")
    private String status;  
}
