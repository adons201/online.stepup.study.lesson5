package ru.stepup.online.model;

import lombok.Data;
import ru.stepup.online.entity.AccountPool;

@Data
public class AccountModel {
    private Integer id;
    private AccountPool accountPoolId;
    private String accountNumber;
}
