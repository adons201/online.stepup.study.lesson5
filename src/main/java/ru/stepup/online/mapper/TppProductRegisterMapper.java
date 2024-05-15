package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse1;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse2;
import ru.stepup.online.entity.TppProductRegister;
import ru.stepup.online.model.TppProductRegisterModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TppProductRegisterMapper {
    TppProductRegisterModel toModel(TppProductRegister tppProductRegister);
    TppProductRegister toEntity(TppProductRegisterModel tppProductRegisterModel);
    @Mappings({
            @Mapping(target="accountId", source="tppProductRegister.account"),
    })
    TppProductRegisterDtoResponse1 toDtoRs1(TppProductRegister tppProductRegister);
    @Mappings({
            @Mapping(target="registerId", source="tppProductRegister.id"),
    })
    TppProductRegisterDtoResponse2 toDtoRs2 (TppProductRegister tppProductRegister);
    List<TppProductRegisterDtoResponse2> toDtoRs2 (List<TppProductRegister> tppProductRegister);
}
