package com.project.backend.repository;

import com.project.backend.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositoty extends JpaRepository<Payment, Long> {
}
