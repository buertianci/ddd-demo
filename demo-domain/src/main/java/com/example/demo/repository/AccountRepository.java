package com.example.demo.repository;

import com.example.demo.domain.entity.Account;
import com.example.demo.types.AccountId;
import com.example.demo.types.AccountNumber;
import com.example.demo.types.UserId;

public interface AccountRepository {
    Account find(AccountId id) throws Exception;
    Account find(AccountNumber accountNumber) throws Exception;
    Account find(UserId userId) throws Exception;
    Account save(Account account) throws Exception;
}
