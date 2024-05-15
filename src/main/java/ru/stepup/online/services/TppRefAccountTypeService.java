package ru.stepup.online.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.mapper.TppRefAccountTypeMapper;
import ru.stepup.online.model.TppRefAccountTypeModel;
import ru.stepup.online.repo.TppRefAccountTypeRepository;

@Service
public class TppRefAccountTypeService {
    private TppRefAccountTypeRepository tppRefAccountTypeRepository;
    @Autowired
    private TppRefAccountTypeMapper tppRefAccountTypeMapper;

    public TppRefAccountTypeService(TppRefAccountTypeRepository tppRefAccountTypeRepository) {
        this.tppRefAccountTypeRepository = tppRefAccountTypeRepository;
    }

    public TppRefAccountTypeModel findByValue(String value) {
        return tppRefAccountTypeMapper.toModel(tppRefAccountTypeRepository.findByValue(value));
    }

}
