package ru.stepup.online.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.dto.request.AgreementDtoRequest;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.response.AgreementDtoResponse;
import ru.stepup.online.dto.response.TppProductDtoResponse;
import ru.stepup.online.entity.Agreement;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.mapper.AgreementMapper;
import ru.stepup.online.mapper.TppProductMapper;
import ru.stepup.online.model.AgreementModel;
import ru.stepup.online.model.TppProductModel;
import ru.stepup.online.repo.AgreementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgreementService {
    private AgreementRepository agreementRepository;

    @Autowired
    private AgreementMapper agreementMapper;
    @Autowired
    private TppProductMapper tppProductMapper;


    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }


    public List<String> findAllByNumber(List<AgreementDtoRequest> agreementDtoRequests) {
        List<String> numbers = agreementDtoRequests.stream().map(AgreementDtoRequest::getNumber).toList();
        List<String> agreements = new ArrayList<>();
        for (String s: numbers) agreements.addAll(agreementRepository.findAllByNumber(s).stream().map(Agreement::getNumber).toList());
        return new ArrayList<>(agreements);
    }

    public List<AgreementDtoResponse> findAllByProductId(TppProduct productId) {
        return agreementMapper.toDtoRs(agreementRepository.findAllByProductId(productId));
    }

    @Transactional
    public List<AgreementModel> insertAgreement(TppProductDtoRequest tppProductDtoRequests, TppProductModel productId) {
        List<AgreementDtoRequest> agreementDtoRequestList = tppProductDtoRequests.getInstanceAgreement();
        List<AgreementModel> agreementModels = new ArrayList<>();
        for (AgreementDtoRequest a : agreementDtoRequestList) {
            agreementModels.add(agreementMapper.toModel(agreementRepository.save(agreementMapper.toEntity(AgreementModel.builder()
                            .productId(tppProductMapper.toEntity(productId))
                            .generalAgreementId(a.getGeneralAgreementId())
                            .supplementaryAgreementId(a.getSupplementaryAgreementId())
                            .arrangementType(a.getArrangementType())
                            .shedulerJobId(a.getShedulerJobId())
                            .number(a.getNumber())
                            .openingDate(a.getOpeningDate())
                            .closingDate(a.getClosingDate())
                            .cancelDate(a.getCancelDate())
                            .validityDuration(a.getValidityDuration())
                            .cancellationReason(a.getCancellationReason())
                            .status(a.getStatus())
                            .interestCalculationDate(a.getInterestCalculationDate())
                            .interestRate(a.getInterestRate())
                            .coefficient(a.getCoefficient())
                            .coefficientAction(a.getCoefficientAction())
                            .minimumInterestRate(a.getMinimumInterestRate())
                            .minimumInterestRateCoefficientAction(a.getMinimumInterestRateCoefficientAction())
                            .maximumInterestRate(a.getMaximalnterestRate())
                            .maximumInterestRateCoefficient(a.getMaximalnterestRateCoefficient())
                            .maximumInterestRateCoefficientAction(a.getMinimumInterestRateCoefficientAction()).build()))));
        }
        return new ArrayList<>(agreementModels);
    }

    public List<String> checkDuplicateAgreement(TppProductDtoRequest tppProductDto) {
        List<AgreementDtoRequest> agreementDtoRequestList = tppProductDto.getInstanceAgreement();
        List<String> listNumbers = findAllByNumber(agreementDtoRequestList);
        List<String> errorParams = new ArrayList<>();
        if (listNumbers.size() > 0) {
            errorParams = listNumbers.stream()
                    .map(x -> "Параметр № Дополнительного соглашения (сделки) Number " + x + " уже существует для ЭП с ИД  "
                            + tppProductDto.getInstanceId()).toList();
        }
        return new ArrayList<>(errorParams);
    }

}
