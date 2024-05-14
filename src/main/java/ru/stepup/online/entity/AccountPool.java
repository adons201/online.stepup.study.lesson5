package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_pool")
@Data
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "branch_code", length = 50)
    private String branchCode;

    @Column(name = "currency_code", length = 30)
    private String currencyCode;

    @Column(name = "mdm_code", length = 50)
    private String mdmCode;

    @Column(name = "priority_code", length = 30)
    private String priorityCode;

    @Column(name = "registry_type_code", length = 50)
    private String registryTypeCode;
}
