package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.Agreement;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.model.AgreementModel;

import java.util.List;

@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Integer> {
    List<Agreement> findAllByNumber(String number);
    List<Agreement> findAllByProductId(TppProduct ProductId);

}
