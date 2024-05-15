package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.TppRefProductClass;
import ru.stepup.online.model.TppRefProductClassModel;

@Mapper(componentModel = "spring")
public interface TppRefProductClassMapper {
    TppRefProductClassModel toModel(TppRefProductClass tppRefProductClass);
    TppRefProductClass toEntity(TppRefProductClassModel tppRefProductClassModel);
}
