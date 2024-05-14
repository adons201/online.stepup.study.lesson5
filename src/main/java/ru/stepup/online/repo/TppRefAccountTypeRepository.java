package ru.stepup.online.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppRefAccountType;
import ru.stepup.online.model.TppRefAccountTypeModel;

import java.util.List;

@Repository
public interface TppRefAccountTypeRepository extends CrudRepository<TppRefAccountType, Integer> {
    List<TppRefAccountType> findAll();
    TppRefAccountType findByValue(String value);
}
