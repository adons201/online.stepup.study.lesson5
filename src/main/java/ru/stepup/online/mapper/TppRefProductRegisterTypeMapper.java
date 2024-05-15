package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.TppRefProductRegisterType;
import ru.stepup.online.model.TppRefProductRegisterTypeModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TppRefProductRegisterTypeMapper {
    TppRefProductRegisterTypeModel toModel(TppRefProductRegisterType tppRefProductRegisterType);
    TppRefProductRegisterType toEntity(TppRefProductRegisterTypeModel tppRefProductRegisterTypeModel);
    List<TppRefProductRegisterTypeModel> toModel(List<TppRefProductRegisterType> tppRefProductRegisterType);
}
