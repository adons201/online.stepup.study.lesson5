package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.model.AccountPoolModel;

@Mapper(componentModel = "spring")
public interface AccountPoolMapper {
    AccountPoolModel accountPoolToAccountPoolModel(AccountPool accountPool);
    AccountPool accountPoolModelToAccountPool(AccountPoolModel accountPoolDto);
}
