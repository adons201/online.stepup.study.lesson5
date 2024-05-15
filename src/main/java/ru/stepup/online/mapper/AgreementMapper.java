package ru.stepup.online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.stepup.online.dto.response.AgreementDtoResponse;
import ru.stepup.online.entity.Agreement;
import ru.stepup.online.model.AgreementModel;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AgreementMapper {
    AgreementModel toModel(Agreement agreement);
    Agreement toEntity(AgreementModel agreementModel);
    List<AgreementModel> toModel(List<Agreement> agreement);
    @Mappings({
            @Mapping(target="supplementaryAgreementId", source="agreement.supplementaryAgreementId"),
    })
    AgreementDtoResponse toDtoRs(Agreement agreement);
    List<AgreementDtoResponse> toDtoRs(List<Agreement> agreement);
}
