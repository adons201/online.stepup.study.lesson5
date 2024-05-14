package ru.stepup.online.model;

import lombok.Data;
import ru.stepup.online.entity.TppProduct;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AgreementModel {
    private Integer id;
    private TppProduct productId;
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Long shedulerJobId;
    private String number;
    private Date openingDate;
    private Date closingDate;
    private Date cancelDate;
    private Long validityDuration;
    private String cancellationReason;
    private String status;
    private Date interestCalculationDate;
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    private String coefficientAction;
    private BigDecimal minimumInterestRate;
    private BigDecimal minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private BigDecimal maximumInterestRate;
    private BigDecimal maximumInterestRateCoefficient;
    private String maximumInterestRateCoefficientAction;
}
