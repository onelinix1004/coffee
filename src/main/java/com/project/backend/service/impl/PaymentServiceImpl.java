package com.project.backend.service.impl;

import com.project.backend.dto.PaymentDTO;
import com.project.backend.repository.PaymentRepositoty;
import com.project.backend.service.PaymentService;
import com.project.backend.entity.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepositoty paymentRepository;

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentDTO> getPaymentById(Long id) {
        return paymentRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public PaymentDTO createPayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    @Override
    public PaymentDTO updatePayment(Long id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setOrder(updatedPayment.getOrder());
        payment.setPaymentDate(updatedPayment.getPaymentDate());
        payment.setPaymentMethod(updatedPayment.getPaymentMethod());
        payment.setTransactionId(updatedPayment.getTransactionId());
        payment.setAmount(updatedPayment.getAmount());

        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .orderId(payment.getOrder().getId())
                .paymentDate(payment.getPaymentDate())
                .paymentMethod(payment.getPaymentMethod())
                .transactionId(payment.getTransactionId())
                .amount(payment.getAmount())
                .build();
    }
}

