package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "agreement")
@Data
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private TppProduct productId;

    @Column(name = "general_agreement_id", length = 50)
    private String generalAgreementId;

    @Column(name = "supplementary_agreement_id", length = 50)
    private String supplementaryAgreementId;

    @Column(name = "arrangement_type", length = 50)
    private String arrangementType;

    @Column(name = "sheduler_job_id")
    private Integer shedulerJobId;

    @Column(name = "number", length = 50)
    private String number;

    @Column(name = "opening_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;

    @Column(name = "closing_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;

    @Column(name = "cancel_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;

    @Column(name = "validity_duration")
    private Integer validityDuration;

    @Column(name = "cancellation_reason", length = 100)
    private String cancellationReason;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "interest_calculation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date interestCalculationDate;

    @Column(name = "interest_rate")
    private Float interestRate;

    @Column(name = "coefficient")
    private Float coefficient;

    @Column(name = "coefficient_action", length = 50)
    private String coefficientAction;

    @Column(name = "minimum_interest_rate")
    private Float minimumInterestRate;

    @Column(name = "minimum_interest_rate_coefficient")
    private Float minimumInterestRateCoefficient;

    @Column(name = "minimum_interest_rate_coefficient_action", length = 50)
    private String minimumInterestRateCoefficientAction;

    @Column(name = "maximal_interest_rate")
    private Float maximalInterestRate;

    @Column(name = "maximal_interest_rate_coefficient")
    private Float maximalInterestRateCoefficient;

    @Column(name = "maximal_interest_rate_coefficient_action", length = 50)
    private String maximalInterestRateCoefficientAction;
}
