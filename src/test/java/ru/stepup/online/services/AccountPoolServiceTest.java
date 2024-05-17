package ru.stepup.online.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.repo.AccountPoolRepository;

import java.util.Optional;


import static org.mockito.Mockito.doReturn;

@ExtendWith({MockitoExtension.class})
class AccountPoolServiceTest {
    @Mock
    AccountPoolRepository accountPoolRepository;

    @InjectMocks
    AccountPoolService accountPoolService;

    @Test
    void findFirstAccountPullId_ReturnValidResponse(){
        //given
        TppProductRegisterDtoRequest tppProductR = new TppProductRegisterDtoRequest();
        tppProductR.setInstanceId(1);
        tppProductR.setRegistryTypeCode("03.012.002_47533_ComSoLd");
        tppProductR.setBranchCode("0022");
        tppProductR.setPriorityCode("00");
        tppProductR.setMdmCode("15");
        Optional<AccountPool> accountPool = Optional.of(new AccountPool());
        doReturn(1).when(this.accountPoolRepository).findFirst(tppProductR.getBranchCode(),tppProductR.getCurrencyCode(),
                tppProductR.getMdmCode(),
                tppProductR.getPriorityCode(),
                tppProductR.getRegistryTypeCode());
        doReturn(accountPool).when(this.accountPoolRepository).findById(1);
        //when
        Optional<AccountPool> accountPool1 = this.accountPoolService.findFirstAccountPullId(tppProductR);
        //then
        Assertions.assertEquals(1, accountPool1.stream().count());
    }
}