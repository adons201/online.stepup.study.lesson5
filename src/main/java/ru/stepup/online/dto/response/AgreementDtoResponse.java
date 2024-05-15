package ru.stepup.online.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class AgreementDtoResponse {
    private String supplementaryAgreementId;
}
