package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.TppRefAccountType;
import ru.stepup.online.model.TppRefAccountTypeModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TppRefAccountTypeMapper {
    TppRefAccountTypeModel tppRefAccountTypeToTppRefAccountTypeModel(TppRefAccountType tppRefAccountType);
    TppRefAccountType tppRefAccountTypeModelToTppRefAccountType(TppRefAccountTypeModel tppRefAccountTypeModel);

    List<TppRefAccountTypeModel> tppRefAccountTypeToTppRefAccountTypeModel(List<TppRefAccountType> tppRefAccountType);
}
