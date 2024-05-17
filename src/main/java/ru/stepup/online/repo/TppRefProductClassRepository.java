package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.TppRefProductClass;

import java.util.Optional;

@Repository
public interface TppRefProductClassRepository extends CrudRepository<TppRefProductClass, Integer> {
    @Query(value ="select value from tpp_ref_product_class ap where value = :productCode  limit 1", nativeQuery = true)
    Optional<String> findByValue(String productCode);
}
