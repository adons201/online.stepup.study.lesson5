package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tpp_product_register")
@Data
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

//    @ManyToOne
//    @JoinColumn(name = "type", referencedColumnName = "value")
//    private TppRefProductRegisterType type;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "account")
    private Integer account;

    @Column(name = "currency_code", length = 30)
    private String currencyCode;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "account_number", length = 25)
    private String accountNumber;
}
