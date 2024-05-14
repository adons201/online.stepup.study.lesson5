package ru.stepup.online.model;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TppProductModel {
    private Integer id;
    private String productCodeId;
    private String clientId;
    private String type;
    private String number;
    private Integer priority;
    private Date dateOfConclusion;
    private Date startDateTime;
    private Date endDateTime;
    private Integer days;
    private BigDecimal penaltyRate;
    private BigDecimal nso;
    private BigDecimal thresholdAmount;
    private String requisiteType;
    private String interestRateType;
}
