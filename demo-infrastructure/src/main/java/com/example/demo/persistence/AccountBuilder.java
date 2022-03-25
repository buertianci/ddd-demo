package com.example.demo.persistence;

import com.example.demo.domain.entity.Account;

public interface AccountBuilder {
    Account toAccount(AccountDO accountDO) throws Exception;

    AccountDO fromAccount(Account account);
}
