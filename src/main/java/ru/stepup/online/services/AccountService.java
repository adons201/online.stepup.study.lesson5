package ru.stepup.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.entity.Account;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.mapper.AccountMapper;
import ru.stepup.online.model.AccountModel;
import ru.stepup.online.repo.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    @Autowired
    AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findFirstAccByAccountPoolId(AccountPool accountPoolId) {
        return accountRepository.findFirstByAccountPoolId(accountPoolId);
    }

    public AccountModel findFirstByAccountPoolId(AccountPool accountPoolId) {
        return accountMapper.toModel(findFirstAccByAccountPoolId(accountPoolId));
    }

}
