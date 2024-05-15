package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.model.TppProductModel;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TppProductMapper {
    TppProductModel toModel(TppProduct tppProduct);
    TppProduct toEntity(TppProductModel tppProductDto);
}
