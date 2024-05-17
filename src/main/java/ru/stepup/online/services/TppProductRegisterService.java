package ru.stepup.online.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse1;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse2;
import ru.stepup.online.entity.TppProductRegister;
import ru.stepup.online.mapper.TppProductRegisterMapper;
import ru.stepup.online.model.AccountModel;
import ru.stepup.online.model.TppProductRegisterModel;
import ru.stepup.online.model.enumeration.ProductState;
import ru.stepup.online.repo.TppProductRegisterRepository;
import ru.stepup.online.services.errors.ErrorParams;

import java.util.List;

@Service
public class TppProductRegisterService {
    private TppProductRegisterRepository tppProductRegisterRepository;
    @Autowired
    private TppProductRegisterMapper tppProductRegisterMapper;
    @Autowired
    private TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountPoolService accountPoolService;

    public TppProductRegisterService(TppProductRegisterRepository tppProductRegisterRepository) {
        this.tppProductRegisterRepository = tppProductRegisterRepository;
    }

    @Transactional
    public TppProductRegisterDtoResponse1 insertTppProductRegister(TppProductRegisterDtoRequest tppProductRegisterDto) {
        AccountModel accountModel = accountService.findFirstByAccountPoolId(accountPoolService.findFirstAccountPullId(tppProductRegisterDto).stream().findFirst().get());

        TppProductRegister tppProductRegister = tppProductRegisterRepository.save(
                tppProductRegisterMapper.toEntity(TppProductRegisterModel.builder()
                        .productId(tppProductRegisterDto.getInstanceId())
                        .type(tppProductRegisterDto.getRegistryTypeCode())
                        .account(accountModel.getId())
                        .currencyCode(tppProductRegisterDto.getCurrencyCode())
                        .state(ProductState.OPEN.getDisc())
                        .accountNumber(accountModel.getAccountNumber())
                        .build()));
        return tppProductRegisterMapper.toDtoRs1(tppProductRegister);
    }

    public ErrorParams checkValidCreateTppProductRegisterDto(TppProductRegisterDtoRequest tppProductRegisterDto) {
        if (checkExistsTppProduct(tppProductRegisterDto.getInstanceId(), tppProductRegisterDto.getRegistryTypeCode()))
            return new ErrorParams("Параметр registryTypeCode тип регистра " + tppProductRegisterDto.getRegistryTypeCode() + " уже существует для ЭП с ИД " + tppProductRegisterDto.getInstanceId(), HttpStatus.BAD_REQUEST);
        if (!tppRefProductRegisterTypeService.checkExistsTppRefProductRegisterType(tppProductRegisterDto.getRegistryTypeCode()))
            return new ErrorParams("Код Продукта " + tppProductRegisterDto.getRegistryTypeCode() + " не найдено в Каталоге продуктов tpp_ref_product_register_type для данного типа Регистра", HttpStatus.NOT_FOUND);
        return new ErrorParams("", HttpStatus.OK);
    }

    public boolean checkExistsTppProduct(Integer id, String type){
        return tppProductRegisterRepository.countProduct(id, type) > 0;
    }

    public List<TppProductRegisterDtoResponse2> findProductRegisters(Integer id){
        List<TppProductRegister> allByProductId = tppProductRegisterRepository.findAllByProductId(id);
        return tppProductRegisterMapper.toDtoRs2(allByProductId);
    }


}
