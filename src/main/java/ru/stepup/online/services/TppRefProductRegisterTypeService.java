package ru.stepup.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.entity.TppRefProductRegisterType;
import ru.stepup.online.mapper.TppRefProductClassMapper;
import ru.stepup.online.mapper.TppRefProductRegisterTypeMapper;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;
import ru.stepup.online.repo.TppRefProductRegisterTypeRepository;

import java.util.List;


@Service
public class TppRefProductRegisterTypeService {
    private TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;
    @Autowired
    private TppRefProductRegisterTypeMapper tppRefProductRegisterTypeMapper;

    public TppRefProductRegisterTypeService(TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository) {
        this.tppRefProductRegisterTypeRepository = tppRefProductRegisterTypeRepository;
    }

    public boolean checkExistsTppRefProductRegisterType(String value){
        if (tppRefProductRegisterTypeRepository.countByValue(value) > 0) return true;
        return false;
    }

    public List<TppRefProductRegisterTypeModel> findTppRefProductRegisterType(String productClassCode, String accountType) {
        return tppRefProductRegisterTypeMapper.tppRefProductRegisterTypeToTppRefProductRegisterTypeModel(
                tppRefProductRegisterTypeRepository.findTppRefProductRegisterType(productClassCode,accountType));
    }

}
