package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.AccountPool;

@Repository
public interface AccountPoolRepository extends CrudRepository<AccountPool, Integer> {
    @Query(value ="select MIN(ap.id) from account_pool ap \n" +
            "where ap.branch_code = :branchCode \n" +
            "and ap.currency_code = :currencyCode \n" +
            "and ap.mdm_code = :mdmCode \n" +
            "and ap.priority_code = :priorityCode \n" +
            "and ap.registry_type_code = :registryTypeCode", nativeQuery = true)
    Integer findFirst(String branchCode, String currencyCode, String mdmCode, String priorityCode, String registryTypeCode);
}
