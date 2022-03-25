package com.example.demo.domain.service.impl;

import com.example.demo.domain.entity.Account;
import com.example.demo.domain.service.AccountTransferService;
import com.example.demo.exception.DailyLimitExceededException;
import com.example.demo.types.ExchangeRate;
import com.example.demo.types.Money;
import org.springframework.stereotype.Service;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {
    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws Exception, DailyLimitExceededException {
        Money sourceMoney =  exchangeRate.exchageTo(targetMoney);
        sourceAccount.deposit(sourceMoney);
        targetAccount.withdraw(targetMoney);
    }
}
