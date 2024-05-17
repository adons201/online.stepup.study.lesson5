package ru.stepup.online.api;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.*;
import ru.stepup.online.services.TppProductRegisterService;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith({MockitoExtension.class})
class TppProductRegisterControllerTest {
    @Mock
    MessageSource messageSource;
    @Mock
    private TppProductRegisterService tppProductRegisterService;

    @InjectMocks
    TppProductRegisterController tppProductController;

    @BeforeEach
    void before(){
        ReflectionTestUtils.setField(tppProductController, "tppProductRegisterService", tppProductRegisterService);
    }

    @SneakyThrows
    @Test
    void createProductRegister_ReturnValidResponse200() {
        //given
        BindingResult result = mock(BindingResult.class);
        MessageSource messageSource = mock(MessageSource.class);
        TppProductRegisterDtoRequest tppProductRegisterDto = new TppProductRegisterDtoRequest();
        ErrorParams errorParams = new ErrorParams("", HttpStatus.OK);
        TppProductDtoResponse tppProduct = new TppProductDtoResponse(1, List.of(new TppProductRegisterDtoResponse2()),List.of(new AgreementDtoResponse()));
        TppProductRegisterDtoResponse1 response1 = new TppProductRegisterDtoResponse1();
        response1.setAccountId(1);
        doReturn(errorParams).when(this.tppProductRegisterService).checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        doReturn(response1).when(this.tppProductRegisterService).insertTppProductRegister(tppProductRegisterDto);
        //when
        ResponseEntity<?> responseEntity = tppProductController.createProductRegister(tppProductRegisterDto,  result, Locale.getDefault());
        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        TppProductRegisterDtoResponse1 response2  = (TppProductRegisterDtoResponse1) ((DataResponse) responseEntity.getBody()).getData();
        Assertions.assertEquals(response1.getAccountId(), response2.getAccountId());
    }


    @SneakyThrows
    @Test
    void createProductRegister_ReturnValidResponse400() {
        //given
        BindingResult result = mock(BindingResult.class);
        MessageSource messageSource = mock(MessageSource.class);
        TppProductRegisterDtoRequest tppProductRegisterDto = new TppProductRegisterDtoRequest();
        ErrorParams errorParams = new ErrorParams("Параметр registryTypeCode тип регистра 1 уже существует для ЭП с ИД 1", HttpStatus.BAD_REQUEST);
        TppProductDtoResponse tppProduct = new TppProductDtoResponse(1, List.of(new TppProductRegisterDtoResponse2()),List.of(new AgreementDtoResponse()));
        TppProductRegisterDtoResponse1 response1 = new TppProductRegisterDtoResponse1();
        response1.setAccountId(1);
        doReturn(errorParams).when(this.tppProductRegisterService).checkValidCreateTppProductRegisterDto(tppProductRegisterDto);
        //when
        ResponseEntity<?> responseEntity = tppProductController.createProductRegister(tppProductRegisterDto,  result, Locale.getDefault());
        //then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("Параметр registryTypeCode тип регистра 1 уже существует для ЭП с ИД 1",
                ((ProblemDetail) responseEntity.getBody()).getProperties().values().stream().findAny().get());
    }
}