package ru.stepup.online.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse1;
import ru.stepup.online.entity.Account;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.entity.TppProductRegister;
import ru.stepup.online.mapper.AccountMapper;
import ru.stepup.online.mapper.TppProductRegisterMapper;
import ru.stepup.online.model.AccountModel;
import ru.stepup.online.model.AgreementModel;
import ru.stepup.online.model.TppProductModel;
import ru.stepup.online.model.TppProductRegisterModel;
import ru.stepup.online.model.enumeration.ProductState;
import ru.stepup.online.repo.AccountPoolRepository;
import ru.stepup.online.repo.TppProductRegisterRepository;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith({MockitoExtension.class})
class TppProductRegisterServiceTest {
    @Mock
    private TppProductRegisterRepository tppProductRegisterRepository;
    @Mock
    private AccountPoolRepository accountPoolRepository;
    @Mock
    private TppProductRegisterMapper tppProductRegisterMapper;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
    @Mock
    private AccountService accountService;
    @Mock
    private AccountPoolService accountPoolService;

    @InjectMocks
    TppProductRegisterService tppProductRegisterService;

    @BeforeEach
    void before(){
        ReflectionTestUtils.setField(tppProductRegisterService, "tppProductRegisterMapper", tppProductRegisterMapper);
        ReflectionTestUtils.setField(tppProductRegisterService, "accountPoolService", accountPoolService);
        ReflectionTestUtils.setField(tppProductRegisterService, "accountService", accountService);
        ReflectionTestUtils.setField(tppProductRegisterService, "tppRefProductRegisterTypeService", tppRefProductRegisterTypeService);
    }

    @Test
    void insertTppProductRegister_ReturnValidResponse() {
        //given
        TppProductRegisterDtoRequest tppProductR = new TppProductRegisterDtoRequest();
        tppProductR.setInstanceId(1);
        Optional<AccountPool> accountPools = Optional.of(new AccountPool());
        AccountPool accountPool = new AccountPool();
        Account account = new Account();
        account.setId(1);
        account.setAccountPoolId(accountPool);
        account.setAccountNumber("3");
        account.setBussy(true);
        AccountModel accountModel = AccountModel.builder().id(1).accountNumber("3").accountPoolId(accountPool).bussy(true).build();
        TppProductRegisterModel tppProductRegisterModel = TppProductRegisterModel.builder()
                .productId(1)
                .type("2")
                .account(3)
                .currencyCode("4")
                .state(ProductState.OPEN.getDisc())
                .accountNumber("5")
                .build();
        TppProductRegisterDtoResponse1 tppProductRegisterDtoResponse1 = new TppProductRegisterDtoResponse1();
        tppProductRegisterDtoResponse1.setAccountId(1);
        doReturn(accountPools).when(this.accountPoolService).findFirstAccountPullId(tppProductR);
        doReturn(accountModel).when(this.accountService).findFirstByAccountPoolId(accountPool);
        doReturn(new TppProductRegister()).when(this.tppProductRegisterMapper).toEntity(ArgumentMatchers.<TppProductRegisterModel> any());
        doReturn(new TppProductRegister()).when(this.tppProductRegisterRepository).save(new TppProductRegister());
        doReturn(tppProductRegisterDtoResponse1).when(this.tppProductRegisterMapper).toDtoRs1(new TppProductRegister());
        //when
        TppProductRegisterDtoResponse1 tppProductRegisterDtoResponse = this.tppProductRegisterService.insertTppProductRegister(tppProductR);
        //then
        Assertions.assertEquals(1, tppProductRegisterDtoResponse.getAccountId());
        Assertions.assertEquals(1, tppProductRegisterDtoResponse.getClass().getDeclaredFields().length);
        Assertions.assertEquals("accountId", tppProductRegisterDtoResponse.getClass().getDeclaredFields()[0].getName());
    }

    @Test
    void checkValidCreateTppProductRegisterDto_ReturnValidResponse404() {
        //given
        TppProductRegisterDtoRequest tppProductRegisterDto  = new TppProductRegisterDtoRequest();
        tppProductRegisterDto.setRegistryTypeCode("1");
        tppProductRegisterDto.setInstanceId(1);
        ErrorParams errorParams;
        doReturn(1).when(this.tppProductRegisterRepository).countProduct(1,"1");
        //when
        errorParams = tppProductRegisterService.checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        //then
        Assertions.assertEquals("Параметр registryTypeCode тип регистра 1 уже существует для ЭП с ИД 1",errorParams.getParam());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,errorParams.getHttpStatus());
    }

    @Test
    void checkValidCreateTppProductRegisterDto_ReturnValidResponse400() {
        //given
        TppProductRegisterDtoRequest tppProductRegisterDto  = new TppProductRegisterDtoRequest();
        tppProductRegisterDto.setRegistryTypeCode("1");
        tppProductRegisterDto.setInstanceId(1);
        ErrorParams errorParams;
        doReturn(0).when(this.tppProductRegisterRepository).countProduct(1,"1");
        doReturn(false).when(this.tppRefProductRegisterTypeService).checkExistsTppRefProductRegisterType("1");
        //when
        errorParams = tppProductRegisterService.checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        //then
        Assertions.assertEquals("Код Продукта 1 не найдено в Каталоге продуктов tpp_ref_product_register_type для данного типа Регистра",errorParams.getParam());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,errorParams.getHttpStatus());
    }

    @Test
    void checkValidCreateTppProductRegisterDto_ReturnValidResponse200() {
        //given
        TppProductRegisterDtoRequest tppProductRegisterDto  = new TppProductRegisterDtoRequest();
        tppProductRegisterDto.setRegistryTypeCode("1");
        tppProductRegisterDto.setInstanceId(1);
        ErrorParams errorParams;
        doReturn(0).when(this.tppProductRegisterRepository).countProduct(1,"1");
        doReturn(true).when(this.tppRefProductRegisterTypeService).checkExistsTppRefProductRegisterType("1");
        //when
        errorParams = tppProductRegisterService.checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        //then
        Assertions.assertEquals("",errorParams.getParam());
        Assertions.assertEquals(HttpStatus.OK,errorParams.getHttpStatus());
    }

}