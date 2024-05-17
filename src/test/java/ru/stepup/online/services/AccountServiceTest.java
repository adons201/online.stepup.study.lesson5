package ru.stepup.online.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ru.stepup.online.dto.request.AgreementDtoRequest;
import ru.stepup.online.entity.Account;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.entity.Agreement;
import ru.stepup.online.mapper.AccountMapper;
import ru.stepup.online.model.AccountModel;
import ru.stepup.online.repo.AccountRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith({MockitoExtension.class})
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    AccountMapper accountMapper;
    @InjectMocks
    AccountService accountService;

    @BeforeEach
    void before(){
        ReflectionTestUtils.setField(accountService, "accountMapper", accountMapper);
    }
    @Test
    void findFirstByAccountPoolId_ReturnValidResponse() {
        //given
        AccountPool accountPool = new AccountPool();
        Account account = new Account();
        account.setId(1);
        account.setAccountPoolId(accountPool);
        account.setAccountNumber("3");
        account.setBussy(true);
        AccountModel accountModel = AccountModel.builder().id(1).accountNumber("3").accountPoolId(accountPool).bussy(true).build();
        doReturn(account).when(this.accountRepository).findFirstByAccountPoolId(accountPool);
        doReturn(accountModel).when(this.accountMapper).toModel(account);
        //when
        AccountModel accountModel1 = this.accountService.findFirstByAccountPoolId(accountPool);
        //then
        Assertions.assertEquals(1, accountModel1.getId());
        Assertions.assertEquals("3", accountModel1.getAccountNumber());
        Assertions.assertEquals(true, accountModel1.getBussy());
    }

}