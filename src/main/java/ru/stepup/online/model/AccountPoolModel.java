package ru.stepup.online.model;

import lombok.Data;

@Data
public class AccountPoolModel {
    private Integer id;
    private String branchCode;
    private String currencyCode;
    private String mdmCode;
    private String priorityCode;
    private String registryTypeCode;
}
