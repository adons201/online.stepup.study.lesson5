package ru.stepup.online.model;

import lombok.Builder;
import lombok.Data;
import ru.stepup.online.entity.AccountPool;

@Data
@Builder
public class AccountModel {
    private Integer id;
    private AccountPool accountPoolId;
    private String accountNumber;
    private Boolean bussy;
}
