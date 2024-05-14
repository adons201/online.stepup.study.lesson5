package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppProduct;



@Repository
public interface TppProductRepository extends CrudRepository<TppProduct, Integer> {
    @Query(value = "select count(*) from tpp_product tp where tp.number = :contractNumber", nativeQuery = true)
    Integer countProduct(String contractNumber);
}
