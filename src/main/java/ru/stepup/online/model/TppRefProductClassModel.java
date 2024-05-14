package ru.stepup.online.model;

import lombok.Data;

@Data
public class TppRefProductClassModel {
    private Integer internalId;
    private String value;
    private String gbiCode;
    private String gbiName;
    private String productRowCode;
    private String subclassCode;
    private String subclassName;
}
