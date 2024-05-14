package ru.stepup.online.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.stepup.online.entity.Account;
import ru.stepup.online.entity.AccountPool;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    //@Query("select test from Test test where test.suiteId.id in(:ids)")
    Account findFirstByAccountPoolId(AccountPool accountPoolId);
}
