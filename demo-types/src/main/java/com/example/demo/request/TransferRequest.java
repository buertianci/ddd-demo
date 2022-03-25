package com.example.demo.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long sourceUserId;
    private String targetAccountNumber;
    private BigDecimal targetAmount;
    private String targetCurrency;
}
