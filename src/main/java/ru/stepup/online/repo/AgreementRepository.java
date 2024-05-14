package ru.stepup.online.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.Agreement;

import java.util.List;

@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Integer> {
    List<Agreement> findAllByNumber(List<String> var1);
}
