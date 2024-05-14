package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.dto.response.TppProductRegisterDtoResponse;
import ru.stepup.online.entity.TppProductRegister;
import ru.stepup.online.model.TppProductRegisterModel;

@Mapper(componentModel = "spring")
public interface TppProductRegisterMapper {
    TppProductRegisterModel tppProductRegisterToTppProductRegisterModel(TppProductRegister tppProductRegister);
    TppProductRegister tppProductRegisterModelToTppProductRegister(TppProductRegisterModel tppProductRegisterModel);
    TppProductRegisterDtoResponse tppProductRegisterToTppProductRegisterDtoResponse(TppProductRegister tppProductRegister);
}
