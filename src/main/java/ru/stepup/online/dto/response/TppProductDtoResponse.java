package ru.stepup.online.dto.response;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TppProductDtoResponse {
    private Integer id;
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
    private Float interestRatePenalty;
    private Float minimalBalance;
    private Float thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private Float taxPercentageRate;
    private Float technicalOverdraftLimitAmount;
    @NotNull(message = "Имя обязательного параметра contractId не заполнено.")
    private Integer contractId;
    @NotNull(message = "Имя обязательного параметра branchCode не заполнено.")
    private String branchCode;
    @NotNull(message = "Имя обязательного параметра IsoCurrencyCode не заполнено.")
    private String IsoCurrencyCode;
    @NotNull(message = "Имя обязательного параметра urgencyCode не заполнено.")
    private String urgencyCode;
    private Integer referenceCode;
    private Object additionalPropertiesVip;
    private List<AgreementDtoResponse> instanceAgreement;
}
