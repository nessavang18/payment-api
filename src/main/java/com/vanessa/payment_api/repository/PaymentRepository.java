package com.vanessa.payment_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanessa.payment_api.entity.Payment;

@Repository
public interface  PaymentRepository extends JpaRepository<Payment, Long>{
    Optional<Payment> findByPaymentReference(String referenceNumber);

}
