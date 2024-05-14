package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppRefProductClass;

@Repository
public interface TppRefProductClassRepository extends CrudRepository<TppRefProductClass, Integer> {
    @Query(value ="select * from tpp_ref_product_class ap where value = :productCode", nativeQuery = true)
    TppRefProductClass findFirst(String productCode);
}
