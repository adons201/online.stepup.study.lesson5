package ru.stepup.online.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TppProductRegisterModel {
    private Integer id;
    private Integer productId;
    private String type;
    private Integer account;
    private String currencyCode;
    private String state;
    private String accountNumber;
}
