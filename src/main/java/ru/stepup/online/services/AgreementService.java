package ru.stepup.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.dto.request.AgreementDtoRequest;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.entity.Agreement;
import ru.stepup.online.mapper.AgreementMapper;
import ru.stepup.online.repo.AgreementRepository;

import java.util.List;

@Service
public class AgreementService {
    private AgreementRepository agreementRepository;

    @Autowired
    private AgreementMapper agreementMapper;

    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    public AgreementDtoRequest insertAgreement(AgreementDtoRequest agreementDto) {
        return agreementDto;
    }

    public List<String> findAllByNumber(List<AgreementDtoRequest> agreementDtoRequests){
        List<String> numbers = agreementDtoRequests.stream().map(AgreementDtoRequest::getNumber).toList();
        return agreementRepository.findAllByNumber(numbers).stream().map(Agreement::getNumber).toList();
    }

}
