package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppRefProductRegisterType;

import java.util.List;

@Repository
public interface TppRefProductRegisterTypeRepository extends CrudRepository<TppRefProductRegisterType, Integer> {
    @Query(value ="select count(*) from tpp_ref_product_register_type tp where tp.value = :value", nativeQuery = true)
    Integer countByValue(String value);
    @Query(value ="select * from tpp_ref_product_register_type tp where tp.product_class_code = :productClassCode and tp.account_type = :accountType", nativeQuery = true)
    List<TppRefProductRegisterType> findTppRefProductRegisterType(String productClassCode, String accountType);
}
