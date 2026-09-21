package com.vanessa.payment_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanessa.payment_api.dto.PaymentRequest;
import com.vanessa.payment_api.dto.PaymentResponse;
import com.vanessa.payment_api.dto.PaymentsResponse;
import com.vanessa.payment_api.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/v1/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(
        summary = "Create payment",
        description= "Returns added payment details"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Payment added")
    })
    @PostMapping("/add")
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }

    @Operation (
        summary = "Get payment by reference number",
        description = "Returns payment details using the payment reference number."
    )
    @ApiResponses ({
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @GetMapping("/{referenceNumber}")
    public PaymentResponse getMethodName(@PathVariable String referenceNumber) {
        return paymentService.getPaymentid(referenceNumber);
    }

        @Operation(
        summary = "Get all payments added",
        description= "Returns list of all payment added in database."
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="")
    })
    @GetMapping("/")
    public PaymentsResponse getMethodName() {
        return paymentService.getPayments();
    }
    
    

}
