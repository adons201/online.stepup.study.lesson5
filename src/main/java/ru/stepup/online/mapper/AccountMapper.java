package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.Account;
import ru.stepup.online.model.AccountModel;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountModel toModel(Account account);
    Account toEntity(AccountModel accountModel);
}
