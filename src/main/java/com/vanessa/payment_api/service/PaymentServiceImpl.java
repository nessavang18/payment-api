package com.vanessa.payment_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vanessa.payment_api.dto.PaymentRequest;
import com.vanessa.payment_api.dto.PaymentResponse;
import com.vanessa.payment_api.dto.PaymentsResponse;
import com.vanessa.payment_api.entity.Payment;
import com.vanessa.payment_api.exception.PaymentNotFoundException;
import com.vanessa.payment_api.repository.PaymentRepository;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository repository;
    private static final String PAYMENT_PREFIX = "REF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    private static final String PAYMENT_CREATED_PREFIX = "CREATED";

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {

        Payment newPayment = Payment.builder()
                .paymentReference(PAYMENT_PREFIX)
                .customerId(request.customerId())
                .amount(request.amount())
                .currency(request.currency())
                .createdAt(LocalDate.now())
                .updateAt(LocalDate.now())
                .build();


        repository.save(newPayment);
        
        log.info("Payment {} is {} ", PAYMENT_CREATED_PREFIX);

        return  buildPaymentResponse(PAYMENT_CREATED_PREFIX, newPayment);
                        
    }

    @Override
    public PaymentResponse getPaymentid(String referenceNumber) {
        Payment payment = repository
            .findByPaymentReference(referenceNumber)
            .orElseThrow(() -> 
                new PaymentNotFoundException("Payment with Reference Number '"+ referenceNumber + "' was not found"));

        return buildPaymentResponse(payment.getStatus(), payment);
    }

    private PaymentResponse buildPaymentResponse(String status, Payment payment){
        return PaymentResponse.builder()
                    .paymentId(payment.getId())
                    .amount(payment.getAmount())
                    .paymentreference(StringUtils.isNotBlank(payment.getPaymentReference()) ?  payment.getPaymentReference() : null )
                    .currency(payment.getCurrency())
                    .customerId(payment.getCustomerId())
                    .status(status)
                    .build();
    }

    @Override
    public PaymentsResponse getPayments() {
        List<Payment> payments = repository.findAll();
       

        List<PaymentResponse> response  = payments.stream()
            .map(payment -> {
                PaymentResponse paymentResponse = new PaymentResponse();
                   paymentResponse.setAmount(payment.getAmount());
                   paymentResponse.setCurrency(payment.getCurrency());
                   paymentResponse.setPaymentId(payment.getId());
                   paymentResponse.setCustomerId(payment.getCustomerId());
                   paymentResponse.setPaymentreference(payment.getPaymentReference());
                   return paymentResponse;
        }).toList();

        return PaymentsResponse.builder()
                .paymentList(response)
                .build();
    }
    
}
