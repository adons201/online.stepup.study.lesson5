package ru.stepup.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepup.online.entity.TppRefProductClass;
import ru.stepup.online.mapper.TppRefProductClassMapper;
import ru.stepup.online.model.TppRefProductClassModel;
import ru.stepup.online.repo.TppRefProductClassRepository;

import java.util.Optional;

@Service
public class TppRefProductClassService {
    private TppRefProductClassRepository tppRefProductClassRepository;
    @Autowired
    private TppRefProductClassMapper tppRefProductClassMapper;

    public TppRefProductClassService(TppRefProductClassRepository tppRefProductClassRepository) {
        this.tppRefProductClassRepository = tppRefProductClassRepository;
    }

    public Optional<String> findByValue(String productCode){
        return tppRefProductClassRepository.findByValue(productCode);
    }

}
