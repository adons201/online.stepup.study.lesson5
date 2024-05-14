package ru.stepup.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.mapper.TppRefProductClassMapper;
import ru.stepup.online.model.TppRefProductClassModel;
import ru.stepup.online.repo.TppRefProductClassRepository;

@Service
public class TppRefProductClassService {
    private TppRefProductClassRepository tppRefProductClassRepository;
    @Autowired
    private TppRefProductClassMapper tppRefProductClassMapper;

    public TppRefProductClassService(TppRefProductClassRepository tppRefProductClassRepository) {
        this.tppRefProductClassRepository = tppRefProductClassRepository;
    }

    public TppRefProductClassModel findFirst(String productCode){
        return tppRefProductClassMapper.tppRefProductClassToTppRefProductClassModel(tppRefProductClassRepository.findFirst(productCode));
    }

}
