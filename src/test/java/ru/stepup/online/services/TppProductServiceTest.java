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
import ru.stepup.online.dto.request.AgreementDtoRequest;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.response.AgreementDtoResponse;
import ru.stepup.online.dto.response.TppProductDtoResponse;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse2;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.entity.TppRefAccountType;
import ru.stepup.online.mapper.TppProductMapper;
import ru.stepup.online.model.TppProductModel;
import ru.stepup.online.model.TppProductRegisterModel;
import ru.stepup.online.model.TppRefAccountTypeModel;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;
import ru.stepup.online.repo.TppProductRepository;
import ru.stepup.online.services.errors.ErrorParams;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith({MockitoExtension.class})
class TppProductServiceTest {
    @Mock
    private AgreementService agreementService;
    @Mock
    private TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
    @Mock
    private TppRefProductClassService tppRefProductClassService;
    @Mock
    private TppRefAccountTypeService tppRefAccountTypeService;
    @Mock
    private TppProductRegisterService tppProductRegisterService;
    @Mock
    private TppProductMapper tppProductMapper;
    @Mock
    private TppProductRepository tppProductRepository;
    @InjectMocks
    private TppProductService tppProductService;

    @BeforeEach
    void before(){
        ReflectionTestUtils.setField(tppProductService, "agreementService", agreementService);
        ReflectionTestUtils.setField(tppProductService, "tppRefProductRegisterTypeService", tppRefProductRegisterTypeService);
        ReflectionTestUtils.setField(tppProductService, "tppProductRegisterService", tppProductRegisterService);
        ReflectionTestUtils.setField(tppProductService, "tppRefProductClassService", tppRefProductClassService);
        ReflectionTestUtils.setField(tppProductService, "tppRefAccountTypeService", tppRefAccountTypeService);
        ReflectionTestUtils.setField(tppProductService, "tppProductMapper", tppProductMapper);
    }

    @Test
    void insertTppProduct_ReturnValidResponse() {
        //given
        TppProductDtoRequest tppProductDto= TppProductDtoRequest.builder().instanceId(1).build();
        TppRefAccountTypeModel tppRefAccountType = new TppRefAccountTypeModel();
        tppRefAccountType.setInternalId(1);
        tppRefAccountType.setValue("Клиентский");
        List<TppProductRegisterDtoResponse2> tppProductRegisterDtoResponse2s = List.of(new TppProductRegisterDtoResponse2(),new TppProductRegisterDtoResponse2());
        List<AgreementDtoResponse> agreementDtoResponseList = List.of(new AgreementDtoResponse(),new AgreementDtoResponse());
        TppProduct tppProduct = new TppProduct();
        tppProduct.setId(1);
        doReturn(tppRefAccountType).when(this.tppRefAccountTypeService).findByValue("Клиентский");
        doReturn(Optional.of("")).when(this.tppRefProductClassService).findByValue(tppProductDto.getProductCode());
        doReturn(tppProduct).when(this.tppProductRepository).save(ArgumentMatchers.<TppProduct> any());
        doReturn(tppProduct).when(this.tppProductMapper).toEntity(ArgumentMatchers.<TppProductModel> any());
        doReturn(tppProductRegisterDtoResponse2s).when(this.tppProductRegisterService).findProductRegisters(ArgumentMatchers.<Integer> any());
        doReturn(agreementDtoResponseList).when(this.agreementService).findAllByProductId(ArgumentMatchers.<TppProduct> any());
        //when
        TppProductDtoResponse tppProductDtoResponse = tppProductService.insertTppProduct(tppProductDto);
        //then
        Assertions.assertEquals(2,tppProductDtoResponse.getSupplementaryAgreementId().size());
        Assertions.assertEquals(2,tppProductDtoResponse.getRegisterId().size());
        Assertions.assertEquals(1,tppProductDtoResponse.getInstanceId());

    }

    @Test
    void checkDuplicateTppProduct_ReturnValidResponse404() {
        //given
        TppProductDtoRequest tppProductDto = TppProductDtoRequest.builder().contractNumber("1").build();
        ErrorParams errorParams;
        Optional<TppProduct> tppProduct= Optional.of(new TppProduct()).stream().peek(x->x.setId(1)).findFirst();
        doReturn(tppProduct).when(this.tppProductRepository).findAllByNumber("1");
        //when
        errorParams = tppProductService.checkDuplicateTppProduct(tppProductDto);
        System.out.println(errorParams.getParam());
        //then
        Assertions.assertEquals("Параметр ContractNumber № договора 1 уже существует для ЭП с ИД 1", errorParams.getParam());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,errorParams.getHttpStatus());
    }

    @Test
    void checkProductCode_ReturnValidResponse404() {
        //given
        TppProductDtoRequest tppProductDto= TppProductDtoRequest.builder().instanceId(1).productCode("1").build();
        TppRefAccountTypeModel tppRefAccountType = new TppRefAccountTypeModel();
        tppRefAccountType.setInternalId(1);
        tppRefAccountType.setValue("Клиентский");
        ErrorParams errorParams;
        doReturn(tppRefAccountType).when(this.tppRefAccountTypeService).findByValue("Клиентский");
        doReturn(Optional.of("")).when(this.tppRefProductClassService).findByValue(tppProductDto.getProductCode());
        //when
        errorParams = tppProductService.checkProductCode(tppProductDto);
        //then
        Assertions.assertEquals("КодПродукта 1 не найдено в Каталоге продуктов tpp_ref_product_class",errorParams.getParam());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,errorParams.getHttpStatus());
    }
}