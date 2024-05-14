package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tpp_product")
@Data
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_code_id")
    private String productCodeId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "number", length = 50)
    private String number;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "date_of_conclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfConclusion;

    @Column(name = "start_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    @Column(name = "days")
    private Integer days;

    @Column(name = "penalty_rate")
    private BigDecimal penaltyRate;

    @Column(name = "nso")
    private BigDecimal nso;

    @Column(name = "threshold_amount")
    private BigDecimal thresholdAmount;

    @Column(name = "requisite_type", length = 50)
    private String requisiteType;

    @Column(name = "interest_rate_type", length = 50)
    private String interestRateType;
}
