package com.project.backend.dto;

import com.project.backend.entity.payment.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private Long id;
    private Long orderId;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private BigDecimal amount;
}

