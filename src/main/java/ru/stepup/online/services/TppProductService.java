package ru.stepup.online.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.stepup.online.dto.request.AgreementDtoRequest;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.TppProductDtoResponse;
import ru.stepup.online.mapper.TppProductMapper;
import ru.stepup.online.model.TppProductModel;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;
import ru.stepup.online.repo.TppProductRepository;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.ArrayList;
import java.util.List;

@Service
public class TppProductService {
    @Autowired
    AgreementService agreementService;
    @Autowired
    TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
    @Autowired
    TppRefProductClassService tppRefProductClassService;
    @Autowired
    TppRefAccountTypeService tppRefAccountTypeService;
    @Autowired
    TppProductRegisterService tppProductRegisterService;

    TppProductMapper tppProductMapper;

    private TppProductRepository tppProductRepository;

    public TppProductService(TppProductRepository tppProductRepository) {
        this.tppProductRepository = tppProductRepository;
    }

    @Transactional
    public TppProductDtoResponse insertTppProduct(TppProductDtoRequest tppProductDto) {
        TppProductModel tppProductModel;
        List<TppRefProductRegisterTypeModel> tppRefProductRegisterTypeModels = tppRefProductRegisterTypeService
                .findTppRefProductRegisterType(tppRefProductClassService.findFirst(tppProductDto.getProductCode()).getValue(),
                        tppRefAccountTypeService.findByValue("Клиентский").getValue());
        if (tppRefProductRegisterTypeModels.size() != 0)
            throw new RuntimeException("КодПродукта " + tppProductDto.getProductCode() + " не найдено в Каталоге продуктов tpp_ref_product_class");


        tppProductModel = TppProductModel.builder().productCodeId(tppProductDto.getProductCode())
                .clientId(tppProductDto.getMdmCode())
                .type(tppProductDto.getProductType())
                .number(tppProductDto.getContractNumber())
                .priority(tppProductDto.getPriority())
                .startDateTime(tppProductDto.getContractDate())
                .penaltyRate(tppProductDto.getInterestRatePenalty())
                .nso(tppProductDto.getMinimalBalance())
                .thresholdAmount(tppProductDto.getThresholdAmount())
                .interestRateType(tppProductDto.getRateType())
                .build();
        tppProductModel.setId(tppProductRepository.save(tppProductMapper.tppProductModelToTppProduct(tppProductModel)).getId());

        for (TppRefProductRegisterTypeModel l : tppRefProductRegisterTypeModels) {
            tppProductRegisterService.insertTppProductRegister(TppProductRegisterDtoRequest.builder()
                    .instanceId(tppProductModel.getId())
                    .currencyCode(tppProductDto.getIsoCurrencyCode())
                    .mdmCode(tppProductDto.getMdmCode())
                    .priorityCode(tppProductDto.getUrgencyCode())
                    .registryTypeCode(l.getValue())
                    .build());
        }

    }

    public ErrorParams checkDuplicateTppProduct(TppProductDtoRequest tppProductDto) {
        if (checkExistsTppProduct(tppProductDto.getContractNumber()))
            return new ErrorParams("Параметр ContractNumber № договора " + tppProductDto.getContractNumber()
                    + " уже существует для ЭП с ИД  " + tppProductDto.getInstanceId(), HttpStatus.BAD_REQUEST);
        return new ErrorParams("", HttpStatus.OK);
    }

    public List<ErrorParams> checkDuplicateAgreement(TppProductDtoRequest tppProductDto) {
        List<AgreementDtoRequest> agreementDtoRequestList = tppProductDto.getInstanceAgreement();
        List<String> listNumbers = agreementService.findAllByNumber(agreementDtoRequestList);
        List<ErrorParams> errorParams = new ArrayList<>();
        if (listNumbers.size() > 0) {
            errorParams = listNumbers.stream()
                    .map(x -> new ErrorParams("Параметр № Дополнительного соглашения (сделки) Number " + x + " уже существует для ЭП с ИД  "
                            + tppProductDto.getInstanceId(), HttpStatus.BAD_REQUEST)).toList();
        }
        return errorParams;
    }

    public boolean checkExistsTppProduct(String contractNumber) {
        if (tppProductRepository.countProduct(contractNumber) > 0) return true;
        return false;
    }


}
