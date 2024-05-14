package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tpp_ref_account_type")
@Data
public class TppRefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    private Integer internalId;

    @Column(name = "value", length = 100, unique = true)
    private String value;

}
