package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tpp_ref_product_register_type")
@Data
public class TppRefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    private Integer internalId;

    @Column(name = "value", length = 100, unique = true, nullable = false)
    private String value;

    @Column(name = "register_type_name", length = 100, nullable = false)
    private String registerTypeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_class_code", referencedColumnName = "value", nullable = false)
    private TppRefProductClass productClassCode;

    @Column(name = "register_type_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTypeStartDate;

    @Column(name = "register_type_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTypeEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type",referencedColumnName = "value")
    private TppRefAccountType accountType;
}
