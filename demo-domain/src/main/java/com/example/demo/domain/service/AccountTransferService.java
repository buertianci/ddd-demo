package com.example.demo.domain.service;

import com.example.demo.domain.entity.Account;
import com.example.demo.exception.DailyLimitExceededException;
import com.example.demo.types.ExchangeRate;
import com.example.demo.types.Money;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws Exception, DailyLimitExceededException;
}
