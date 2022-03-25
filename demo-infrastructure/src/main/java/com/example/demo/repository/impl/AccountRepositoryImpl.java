package com.example.demo.repository.impl;

import com.example.demo.domain.entity.Account;
import com.example.demo.exception.BusinessException;
import com.example.demo.persistence.AccountBuilder;
import com.example.demo.persistence.AccountDO;
import com.example.demo.persistence.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.types.AccountId;
import com.example.demo.types.AccountNumber;
import com.example.demo.types.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private AccountMapper accountDAO;

    @Autowired
    private AccountBuilder accountBuilder;

    @Override
    public Account find(AccountId id) throws Exception {
        AccountDO accountDO = accountDAO.selectById(id.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) throws Exception {
        AccountDO accountDO = accountDAO.selectByAccountNumber(accountNumber.getValue());
        if (accountDO == null){
            throw new BusinessException(String.format("账户[%s]不存在", accountNumber.getValue()));
        }
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId) throws Exception {
        AccountDO accountDO = accountDAO.selectByUserId(userId.getId());
        if (accountDO == null){
            throw new BusinessException("账户不存在");
        }
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account save(Account account) throws Exception {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        if (accountDO.getId() == null) {
            accountDAO.insert(accountDO);
        } else {
            accountDAO.update(accountDO);
        }
        return accountBuilder.toAccount(accountDO);
    }

}
