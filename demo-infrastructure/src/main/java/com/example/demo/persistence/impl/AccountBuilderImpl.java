package com.example.demo.persistence.impl;

import com.example.demo.domain.entity.Account;
import com.example.demo.persistence.AccountBuilder;
import com.example.demo.persistence.AccountDO;
import com.example.demo.types.*;
import org.springframework.stereotype.Component;

@Component
public class AccountBuilderImpl implements AccountBuilder {
    @Override
    public Account toAccount(AccountDO accountDO) throws Exception {
        Account account = new Account();
        account.setId(new AccountId(accountDO.getId()));
        account.setAccountNumber(new AccountNumber(accountDO.getAccountNumber()));
        account.setUserId(new UserId(accountDO.getUserId()));
        Currency currency = new Currency(accountDO.getCurrency());
        account.setAvailable(new Money(accountDO.getAvailableAmout(), currency));
        account.setDailyLimit(new Money(accountDO.getDailyLimitAmout(), currency));
        return account;
    }

    @Override
    public AccountDO fromAccount(Account account) {
        AccountDO accountDO = new AccountDO();
        if (account.getId() != null){
            accountDO.setId(account.getId().getValue());
        }
        accountDO.setUserId(account.getUserId().getId());
        accountDO.setAccountNumber(account.getAccountNumber().getValue());
        accountDO.setAvailableAmout(account.getAvailable().getAmout());
        accountDO.setDailyLimitAmout(account.getDailyLimit().getAmout());
        accountDO.setCurrency(account.getCurrency().getValue());
        return accountDO;
    }
}
