package ru.stepup.online.dto.response;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TppProductDtoResponse {
    private Integer instanceId;
    private List<TppProductRegisterDtoResponse2> registerId;
    private List<AgreementDtoResponse> supplementaryAgreementId;
}
