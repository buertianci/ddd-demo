package com.example.demo.application.impl;

import com.example.demo.application.TransferService;
import com.example.demo.common.Result;
import com.example.demo.domain.entity.Account;
import com.example.demo.domain.service.AccountTransferService;
import com.example.demo.domain.types.AuditMessage;
import com.example.demo.exception.DailyLimitExceededException;
import com.example.demo.external.ExchangeRateService;
import com.example.demo.messaging.AuditMessageProducer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private AccountTransferService accountTransferService;
    private AuditMessageProducer auditMessageProducer;

    @Override
    public Result<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) throws Exception, DailyLimitExceededException {
        Money targetMoney = new Money(targetAmount, new Currency(targetCurrency));
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(message);

        return Result.success(true);
    }
}
