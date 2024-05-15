package ru.stepup.online.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TppProductDtoRequest {
    private Integer instanceId;
    @NotNull(message = "Имя обязательного параметра productType не заполнено.")
    private String productType;
    @NotNull(message = "Имя обязательного параметра productCode не заполнено.")
    private String productCode;
    @NotNull(message = "Имя обязательного параметра registerType не заполнено.")
    private String registerType ;
    @NotNull(message = "Имя обязательного параметра mdmCode не заполнено.")
    private String mdmCode;
    @NotNull(message = "Имя обязательного параметра contractNumber не заполнено.")
    private String contractNumber;
    @NotNull(message = "Имя обязательного параметра contractDate не заполнено.")
    private Date contractDate;
    @NotNull(message = "Имя обязательного параметра priority не заполнено.")
    private Integer priority;
    private BigDecimal interestRatePenalty;
    private BigDecimal minimalBalance;
    private BigDecimal thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private BigDecimal taxPercentageRate;
    private BigDecimal technicalOverdraftLimitAmount;
    @NotNull(message = "Имя обязательного параметра contractId не заполнено.")
    private Integer contractId;
    @NotNull(message = "Имя обязательного параметра branchCode не заполнено.")
    @JsonProperty("BranchCode")
    private String branchCode;
    @NotNull(message = "Имя обязательного параметра IsoCurrencyCode не заполнено.")
    @JsonProperty("IsoCurrencyCode")
    private String isoCurrencyCode;
    @NotNull(message = "Имя обязательного параметра urgencyCode не заполнено.")
    private String urgencyCode;
    @JsonProperty("ReferenceCode")
    private Integer referenceCode;
    private Object additionalPropertiesVip;
    private List<AgreementDtoRequest> instanceAgreement;
}
