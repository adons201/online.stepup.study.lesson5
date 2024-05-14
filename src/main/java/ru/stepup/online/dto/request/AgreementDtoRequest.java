package ru.stepup.online.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
public class AgreementDtoRequest {
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    @NotNull(message = "Имя обязательного параметра number не заполнено.")
    private String number;
    @NotNull(message = "Имя обязательного параметра openingDate не заполнено.")
    private Date openingDate;
    private Date closingDate;
    private Date cancelDate;
    private String cancellationReason;
    private String status;
    private Date interestCalculationDate;
    private Float interestRate;
    private Float coefficient;
    private String coefficientAction;
    private Float minimumInterestRate;
    private String minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private BigDecimal maximalnterestRate;
    private BigDecimal maximalnterestRateCoefficient;
    private String maximalnterestRateCoefficientAction;


}
