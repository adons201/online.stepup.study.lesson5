package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppProductRegister;

@Repository
public interface TppProductRegisterRepository extends CrudRepository<TppProductRegister, Integer> {
    @Query(value = "select count(*) from tpp_product_register tp where tp.product_id = :id and tp.type = :type", nativeQuery = true)
    Integer countProduct(Integer id, String type);
}
