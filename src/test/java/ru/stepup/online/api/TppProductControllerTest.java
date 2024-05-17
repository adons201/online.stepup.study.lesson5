package ru.stepup.online.api;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.response.AgreementDtoResponse;
import ru.stepup.online.dto.response.DataResponse;
import ru.stepup.online.dto.response.TppProductDtoResponse;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse2;
import ru.stepup.online.services.AgreementService;
import ru.stepup.online.services.TppProductService;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith({MockitoExtension.class})
class TppProductControllerTest {
    @Mock
    MessageSource messageSource;
    @Mock
    private TppProductService tppProductService;
    @Mock
    private AgreementService agreementService;
    @InjectMocks
    TppProductController tppProductController;

    @BeforeEach
    void before(){
        ReflectionTestUtils.setField(tppProductController, "agreementService", agreementService);
    }
    @SneakyThrows
    @Test
    void createProduct_ReturnValidResponse200() {
        //given
        BindingResult result = mock(BindingResult.class);
        MessageSource messageSource = mock(MessageSource.class);
        TppProductDtoRequest tppProductDto = new TppProductDtoRequest();
        ErrorParams errorParams = new ErrorParams("",HttpStatus.OK);
        TppProductDtoResponse tppProduct = new TppProductDtoResponse(1,List.of(new TppProductRegisterDtoResponse2()),List.of(new AgreementDtoResponse()));
        doReturn(errorParams).when(this.tppProductService).checkDuplicateTppProduct(tppProductDto);
        doReturn(List.of()).when(this.agreementService).checkDuplicateAgreement(tppProductDto);
        doReturn(errorParams).when(this.tppProductService).checkProductCode(tppProductDto);
        doReturn(tppProduct).when(this.tppProductService).insertTppProduct(tppProductDto);
        //when
        ResponseEntity<?> responseEntity = tppProductController.createProduct(tppProductDto,  result, Locale.getDefault());
        //then
        Assertions.assertNotNull(responseEntity);
        TppProductDtoResponse tppProduct2  = (TppProductDtoResponse) ((DataResponse) responseEntity.getBody()).getData();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(1, tppProduct2.getInstanceId());
        Assertions.assertEquals(1, tppProduct2.getRegisterId().size());
        Assertions.assertEquals(1, tppProduct2.getSupplementaryAgreementId().size());
    }


    @SneakyThrows
    @Test
    void createProduct_ReturnValidResponse400() {
        //given
        BindingResult result = mock(BindingResult.class);
        MessageSource messageSource = mock(MessageSource.class);
        TppProductDtoRequest tppProductDto = new TppProductDtoRequest();
        ErrorParams errorParams = new ErrorParams("Параметр ContractNumber № договора 1 уже существует для ЭП с ИД 1", HttpStatus.BAD_REQUEST);
        TppProductDtoResponse tppProduct = new TppProductDtoResponse(1,List.of(new TppProductRegisterDtoResponse2()),List.of(new AgreementDtoResponse()));
        doReturn(errorParams).when(this.tppProductService).checkDuplicateTppProduct(tppProductDto);
        //when
        ResponseEntity<?> responseEntity = tppProductController.createProduct(tppProductDto,  result, Locale.getDefault());
        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("Параметр ContractNumber № договора 1 уже существует для ЭП с ИД 1",
                ((ProblemDetail) responseEntity.getBody()).getProperties().values().stream().findAny().get());
    }
}