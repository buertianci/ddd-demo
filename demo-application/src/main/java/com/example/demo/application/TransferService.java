package com.example.demo.application;

import com.example.demo.common.Result;
import com.example.demo.exception.DailyLimitExceededException;

import java.math.BigDecimal;

public interface TransferService {

    Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) throws Exception, DailyLimitExceededException;

}