package ru.stepup.online.services;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.util.ReflectionTestUtils;
import ru.stepup.online.dto.request.AgreementDtoRequest;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.AgreementDtoResponse;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.entity.Agreement;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.mapper.AgreementMapper;
import ru.stepup.online.mapper.TppProductMapper;
import ru.stepup.online.model.AgreementModel;
import ru.stepup.online.model.TppProductModel;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;
import ru.stepup.online.repo.AccountPoolRepository;
import ru.stepup.online.repo.AgreementRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith({MockitoExtension.class})
class AgreementServiceTest {
    @Mock
    private TppProductMapper tppProductMapper;
    @Mock
    private AgreementMapper agreementMapper;
    @Mock
    private AgreementRepository agreementRepository;

    @InjectMocks
    private AgreementService agreementService ;

    @BeforeEach
    void before(){
        ReflectionTestUtils.setField(agreementService, "tppProductMapper", tppProductMapper);
        ReflectionTestUtils.setField(agreementService,"agreementMapper", agreementMapper);
    }

    @Test
    void findAllByNumber_ReturnValidResponse(){
        //given
        List<AgreementDtoRequest> agreementDtoRequests = List.of(AgreementDtoRequest.builder().number("1").build());
        List<Agreement> agreements = List.of(new Agreement());
        //when
        doReturn(agreements).when(this.agreementRepository).findAllByNumber("1");
        //then
        Assertions.assertEquals(1, this.agreementService.findAllByNumber(agreementDtoRequests).size());
    }

    @Test
    void findAllByProductId_ReturnValidResponse(){
        //given
        TppProduct tppProduct = new TppProduct();
        Agreement agreement = new Agreement();
        AgreementDtoResponse agreementDtoResponse = new AgreementDtoResponse();
        agreementDtoResponse.setSupplementaryAgreementId("Num1");
        agreement.setSupplementaryAgreementId("Num1");
        List<Agreement> agreements = List.of(agreement);
        List<AgreementDtoResponse> agreementDtoResponses = List.of(agreementDtoResponse);
        doReturn(agreements).when(this.agreementRepository).findAllByProductId(tppProduct);
        doReturn(agreementDtoResponses).when(this.agreementMapper).toDtoRs(agreements);
        //when
        String s = this.agreementService.findAllByProductId(tppProduct).get(0).getSupplementaryAgreementId();
        //then
        Assertions.assertEquals("Num1", s);
    }


    @Test
    void insertAgreement_ReturnValidResponse(){
        //given
        TppProductDtoRequest tppProduct = new TppProductDtoRequest();
        TppProductModel tppProductModel= TppProductModel.builder().id(1).build();
        List<AgreementModel> agreementModels;
        AgreementDtoRequest agreementDtoRequest = AgreementDtoRequest.builder()
                .generalAgreementId("1")
                .supplementaryAgreementId("2")
                .arrangementType("3")
                .shedulerJobId(4)
                .number("5")
                .validityDuration(6)
                .cancellationReason("7")
                .status("8")
                .interestRate(9f)
                .coefficient(10f)
                .coefficientAction("11")
                .minimumInterestRate(12f)
                .minimumInterestRateCoefficientAction("13")
                .maximalnterestRate(14f)
                .maximalnterestRateCoefficient(15f)
                .maximalnterestRateCoefficientAction("16").build();
        List<AgreementDtoRequest> agreementDtoRequests = List.of(agreementDtoRequest);
        tppProduct.setInstanceAgreement(agreementDtoRequests);
        AgreementModel agreementModel = AgreementModel.builder().id(1)
                .generalAgreementId(agreementDtoRequest.getGeneralAgreementId())
                .supplementaryAgreementId(agreementDtoRequest.getSupplementaryAgreementId())
                .arrangementType(agreementDtoRequest.getArrangementType())
                .shedulerJobId(agreementDtoRequest.getShedulerJobId())
                .number(agreementDtoRequest.getNumber())
                .openingDate(agreementDtoRequest.getOpeningDate())
                .closingDate(agreementDtoRequest.getClosingDate())
                .cancelDate(agreementDtoRequest.getCancelDate())
                .validityDuration(agreementDtoRequest.getValidityDuration())
                .cancellationReason(agreementDtoRequest.getCancellationReason())
                .status(agreementDtoRequest.getStatus())
                .interestCalculationDate(agreementDtoRequest.getInterestCalculationDate())
                .interestRate(agreementDtoRequest.getInterestRate())
                .coefficient(agreementDtoRequest.getCoefficient())
                .coefficientAction(agreementDtoRequest.getCoefficientAction())
                .minimumInterestRate(agreementDtoRequest.getMinimumInterestRate())
                .minimumInterestRateCoefficientAction(agreementDtoRequest.getMinimumInterestRateCoefficientAction())
                .maximumInterestRate(agreementDtoRequest.getMaximalnterestRate())
                .maximumInterestRateCoefficient(agreementDtoRequest.getMaximalnterestRateCoefficient())
                .maximumInterestRateCoefficientAction(agreementDtoRequest.getMaximalnterestRateCoefficientAction()).build();

        doReturn(new Agreement()).when(this.agreementMapper).toEntity(ArgumentMatchers.<AgreementModel> any());
        doReturn(new Agreement()).when(this.agreementRepository).save(ArgumentMatchers.<Agreement> any());
        doReturn(agreementModel).when(this.agreementMapper).toModel(ArgumentMatchers.<Agreement> any());
        //when
        agreementModels = this.agreementService.insertAgreement(tppProduct, tppProductModel);
        //then
        Assertions.assertEquals(1, agreementModels.size());
        Assertions.assertEquals("1", agreementModels.get(0).getGeneralAgreementId());
        Assertions.assertEquals("2", agreementModels.get(0).getSupplementaryAgreementId());
        Assertions.assertEquals("3", agreementModels.get(0).getArrangementType());
        Assertions.assertEquals(4, agreementModels.get(0).getShedulerJobId());
        Assertions.assertEquals("5", agreementModels.get(0).getNumber());
        Assertions.assertEquals(6, agreementModels.get(0).getValidityDuration());
        Assertions.assertEquals("7", agreementModels.get(0).getCancellationReason());
        Assertions.assertEquals("8", agreementModels.get(0).getStatus());
        Assertions.assertEquals(9f, agreementModels.get(0).getInterestRate());
        Assertions.assertEquals(10f, agreementModels.get(0).getCoefficient());
        Assertions.assertEquals("11", agreementModels.get(0).getCoefficientAction());
        Assertions.assertEquals(12f, agreementModels.get(0).getMinimumInterestRate());
        Assertions.assertEquals("13", agreementModels.get(0).getMinimumInterestRateCoefficientAction());
        Assertions.assertEquals(14f, agreementModels.get(0).getMaximumInterestRate());
        Assertions.assertEquals(15f, agreementModels.get(0).getMaximumInterestRateCoefficient());
        Assertions.assertEquals("16", agreementModels.get(0).getMaximumInterestRateCoefficientAction());

    }
    @Test
    void checkDuplicateAgreement_ReturnValidResponse(){
        //given
        TppProductDtoRequest tppProductDto = new TppProductDtoRequest();
        List<AgreementDtoRequest> agreementDtoRequests = List.of(AgreementDtoRequest.builder().number("1").build());
        tppProductDto.setInstanceAgreement(agreementDtoRequests);
        List<Agreement> agreements = List.of(new Agreement());
        agreements.get(0).setProductId(new TppProduct());
        doReturn(agreements).when(this.agreementRepository).findAllByNumber("1");
        //when
        int size = this.agreementService.checkDuplicateAgreement(tppProductDto).size();
        //then
        Assertions.assertEquals(1, size);
    }

}