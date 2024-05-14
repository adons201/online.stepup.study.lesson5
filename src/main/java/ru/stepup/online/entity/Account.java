package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_pool_id", referencedColumnName = "id")
    private AccountPool accountPoolId;

    @Column(name = "account_number", length = 25)
    private String accountNumber;

    @Column(name = "bussy")
    private Boolean bussy;
}
