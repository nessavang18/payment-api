package com.vanessa.payment_api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name="PAYMENT")
public class Payment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="PAYMENT_REFERENCE")
    private String paymentReference;

    @Column(name="CUSTOMER_ID")
    private String customerId;

    @Column(name="AMOUNT")
    private BigDecimal amount;

    @Column(name="CURRENCY")
    private String currency;
    
    @Column(name="STATUS")
    private String status;

    @Column(name="CREATED_AT")
    private LocalDate createdAt;

    @Column(name="UPDATED_AT")
    private LocalDate updateAt;

}
