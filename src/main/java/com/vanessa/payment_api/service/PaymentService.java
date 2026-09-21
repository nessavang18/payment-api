package com.vanessa.payment_api.service;

import com.vanessa.payment_api.dto.PaymentRequest;
import com.vanessa.payment_api.dto.PaymentResponse;
import com.vanessa.payment_api.dto.PaymentsResponse;

public interface PaymentService {

    PaymentsResponse getPayments();
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse getPaymentid(String id);
    
}
