package ru.stepup.online.model;

import lombok.Builder;
import lombok.Data;
import ru.stepup.online.entity.TppProduct;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class AgreementModel {
    private Integer id;
    private TppProduct productId;
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    private String number;
    private Date openingDate;
    private Date closingDate;
    private Date cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    private Date interestCalculationDate;
    private Float interestRate;
    private Float coefficient;
    private String coefficientAction;
    private Float minimumInterestRate;
    private Float minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private Float maximumInterestRate;
    private Float maximumInterestRateCoefficient;
    private String maximumInterestRateCoefficientAction;
}
