package com.vanessa.payment_api;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vanessa.payment_api.entity.Payment;
import com.vanessa.payment_api.repository.PaymentRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PaymentRepository repository;

	@BeforeEach
	void setup() {
		repository.deleteAll();

		Payment payment = new Payment();
		payment.setAmount(new BigDecimal("150000.00"));
		payment.setPaymentReference("PAY-123");
		payment.setCustomerId("CUST-001");
		payment.setCurrency("PHP");
		payment.setStatus("PENDING");

		repository.save(payment);
	}

	@Test
	void shouldGetPaymentReferenceNumber() throws Exception{
		Payment payment = repository.findAll().get(0);

		mockMvc.perform(
                get("/v1/api/payments/{referenceNumber}", 
						 payment.getPaymentReference())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.paymentreference")
                .value(payment.getPaymentReference()))
        .andExpect(jsonPath("$.customerId")
                .value("CUST-001"))
        .andExpect(jsonPath("$.amount")
                .value(150000.00))
        .andExpect(jsonPath("$.currency")
                .value("PHP"))
        .andExpect(jsonPath("$.status")
                .value("PENDING"));
    }

	@Test
void shouldReturn404WhenPaymentDoesNotExist() throws Exception {

    mockMvc.perform(
            get("/v1/api/payments/{referenceNumber}", "PAY-111")
    )
    .andExpect(status().isNotFound())
    .andExpect(jsonPath("$.status").value(404))
    .andExpect(jsonPath("$.error")
            .value("PAYMENT_NOT_FOUND"))
    .andExpect(jsonPath("$.message")
            .value("Payment with Reference Number 'PAY-111' was not found"));
}

}
