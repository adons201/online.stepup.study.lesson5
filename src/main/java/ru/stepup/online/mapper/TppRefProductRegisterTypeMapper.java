package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.TppRefProductRegisterType;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TppRefProductRegisterTypeMapper {
    TppRefProductRegisterTypeModel tppRefProductRegisterTypeToTppRefProductRegisterTypeModel(TppRefProductRegisterType tppRefProductRegisterType);
    TppRefProductRegisterType tppRefProductRegisterTypeModelToTppRefProductRegisterType(TppRefProductRegisterTypeModel tppRefProductRegisterTypeModel);
    List<TppRefProductRegisterTypeModel> tppRefProductRegisterTypeToTppRefProductRegisterTypeModel(List<TppRefProductRegisterType> tppRefProductRegisterType);
}
