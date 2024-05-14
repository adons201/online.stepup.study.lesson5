package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import ru.stepup.online.entity.TppProduct;
import ru.stepup.online.model.TppProductModel;

@Mapper(componentModel = "spring")
public interface TppProductMapper {
    TppProductModel tppProductToTppProductModel(TppProduct TppProduct);
    TppProduct tppProductModelToTppProduct(TppProductModel TppProductDto);
}
