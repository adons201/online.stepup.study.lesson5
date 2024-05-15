package ru.stepup.online.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppProduct;

import java.util.List;
import java.util.Optional;


@Repository
public interface TppProductRepository extends CrudRepository<TppProduct, Integer> {
    Optional<TppProduct> findAllByNumber(String contractNumber);
    Optional<TppProduct> findById(Integer id);


}
