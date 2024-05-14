package ru.stepup.online.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tpp_ref_product_class")
@Data
public class TppRefProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    private Integer internalId;

    @Column(name = "value", length = 100, unique = true)
    private String value;

    @Column(name = "gbi_code", length = 50)
    private String gbiCode;

    @Column(name = "gbi_name", length = 100)
    private String gbiName;

    @Column(name = "product_row_code", length = 50)
    private String productRowCode;

    @Column(name = "product_row_name", length = 100)
    private String productRowName;

    @Column(name = "subclass_code", length = 50)
    private String subclassCode;

    @Column(name = "subclass_name", length = 100)
    private String subclassName;
}
