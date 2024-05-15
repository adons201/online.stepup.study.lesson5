package ru.stepup.online.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.stepup.online.dto.request.TppProductDtoRequest;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.AgreementDtoResponse;
import ru.stepup.online.dto.response.TppProductDtoResponse;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse1;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse2;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.entity.TppRefAccountType;
import ru.stepup.online.mapper.TppProductMapper;
import ru.stepup.online.model.AgreementModel;
import ru.stepup.online.model.TppProductModel;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;
import ru.stepup.online.repo.TppProductRepository;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    TppProductMapper tppProductMapper;

    private TppProductRepository tppProductRepository;

    public TppProductService(TppProductRepository tppProductRepository) {
        this.tppProductRepository = tppProductRepository;
    }

    @Transactional
    public TppProductDtoResponse insertTppProduct(TppProductDtoRequest tppProductDto) {
        TppProductModel tppProductModel;
        String acc = tppRefAccountTypeService.findByValue("Клиентский").getValue();
        String value = tppRefProductClassService.findFirst(tppProductDto.getProductCode()).getValue();
        List<TppRefProductRegisterTypeModel> tppRefProductRegisterTypeModels = tppRefProductRegisterTypeService.findTppRefProductRegisterType(value,acc);

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
        tppProductModel.setId(tppProductRepository.save(tppProductMapper.toEntity(tppProductModel)).getId());

        for (TppRefProductRegisterTypeModel l : tppRefProductRegisterTypeModels) {
            tppProductRegisterService.insertTppProductRegister(TppProductRegisterDtoRequest.builder()
                    .instanceId(tppProductModel.getId())
                    .currencyCode(tppProductDto.getIsoCurrencyCode())
                    .mdmCode(tppProductDto.getMdmCode())
                    .priorityCode(tppProductDto.getUrgencyCode())
                    .registryTypeCode(l.getValue())
                    .branchCode(tppProductDto.getBranchCode())
                    .build());
        }
        return getTppProductResponse(tppProductMapper.toEntity(tppProductModel));
    }


    @Transactional
    public TppProductDtoResponse insertTppProduct(TppProductDtoRequest tppProductDtoRequests, Integer productId) {
        TppProductModel tppProductModel = tppProductMapper.toModel(tppProductRepository.findById(productId).get());
        List<AgreementModel> agreementModels = agreementService.insertAgreement(tppProductDtoRequests, tppProductModel);
        return getTppProductResponse(tppProductMapper.toEntity(tppProductModel));
    }

    public ErrorParams checkDuplicateTppProduct(TppProductDtoRequest tppProductDto) {
        Optional<TppProduct> allByNumber = tppProductRepository.findAllByNumber(tppProductDto.getContractNumber());
        TppProduct tppProduct = allByNumber.orElse(null);
        if (tppProduct != null) {
            return new ErrorParams("Параметр ContractNumber № договора " + tppProductDto.getContractNumber()
                    + " уже существует для ЭП с ИД  " + tppProduct.getId(), HttpStatus.BAD_REQUEST);
        }
        return new ErrorParams("", HttpStatus.OK);
    }

    public ErrorParams checkProductCode(TppProductDtoRequest tppProductDto) {
        String acc = tppRefAccountTypeService.findByValue("Клиентский").getValue();
        String value = tppRefProductClassService.findFirst(tppProductDto.getProductCode()).getValue();
        List<TppRefProductRegisterTypeModel> tppRefProductRegisterTypeModels = tppRefProductRegisterTypeService.findTppRefProductRegisterType(value,acc);
        if (tppRefProductRegisterTypeModels.size() == 0)
           return new ErrorParams("КодПродукта " + tppProductDto.getProductCode() + " не найдено в Каталоге продуктов tpp_ref_product_class", HttpStatus.NOT_FOUND);
        return new ErrorParams("", HttpStatus.OK);
    }

    public TppProductDtoResponse getTppProductResponse(TppProduct product){
        List<TppProductRegisterDtoResponse2> productRegisters = tppProductRegisterService.findProductRegisters(product.getId());
        List<AgreementDtoResponse> allByProductId = agreementService.findAllByProductId(product);
        return new TppProductDtoResponse(product.getId(), productRegisters, allByProductId);
    }

}
