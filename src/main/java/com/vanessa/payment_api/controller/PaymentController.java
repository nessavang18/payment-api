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



@RestController
@RequestMapping("/v1/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    

    @PostMapping("/add")
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }

    @GetMapping("/{referenceNumber}")
    public PaymentResponse getMethodName(@PathVariable String referenceNumber) {
        return paymentService.getPaymentid(referenceNumber);
    }

    @GetMapping("/")
    public PaymentsResponse getMethodName() {
        return paymentService.getPayments();
    }
    
    

}
