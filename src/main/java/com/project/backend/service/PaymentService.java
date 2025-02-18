package com.project.backend.service;


import com.project.backend.dto.PaymentDTO;
import com.project.backend.entity.payment.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<PaymentDTO> getAllPayments();
    Optional<PaymentDTO> getPaymentById(Long id);
    PaymentDTO createPayment(Payment payment);
    PaymentDTO updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
}

