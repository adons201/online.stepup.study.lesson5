package ru.stepup.online.model;

import lombok.Data;
import ru.stepup.online.entity.TppRefAccountType;
import ru.stepup.online.entity.TppRefProductClass;

import java.util.Date;

@Data
public class TppRefProductRegisterTypeModel {
    private Integer internalId;
    private String value;
    private String registerTypeName;
    private TppRefProductClass productClassCode;
    private Date registerTypeStartDate;
    private Date registerTypeEndDate;
    private TppRefAccountType accountType;
}
