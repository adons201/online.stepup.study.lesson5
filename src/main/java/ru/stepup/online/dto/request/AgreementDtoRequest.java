package ru.stepup.online.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementDtoRequest {
    @JsonProperty("GeneralAgreementId")
    private String generalAgreementId;
    @JsonProperty("SupplementaryAgreementId")
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    @NotNull(message = "Имя обязательного параметра number не заполнено.")
    @JsonProperty("Number")
    private String number;
    @NotNull(message = "Имя обязательного параметра openingDate не заполнено.")
    private Date openingDate;
    private Date closingDate;
    @JsonProperty("CancelDate")
    private Date cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    @JsonProperty("Status")
    private String status;
    private Date interestCalculationDate;
    private Float interestRate;
    private Float coefficient;
    private String coefficientAction;
    private Float minimumInterestRate;
    private String minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private Float maximalnterestRate;
    private Float maximalnterestRateCoefficient;
    private String maximalnterestRateCoefficientAction;


}
