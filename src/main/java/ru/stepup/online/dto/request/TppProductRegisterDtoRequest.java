package ru.stepup.online.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TppProductRegisterDtoRequest {
    @NotNull(message = "Имя обязательного параметра instanceId не заполнено.")
    private Integer instanceId;
    private String registryTypeCode;
    private String accountType;
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private Long clientId;
    private String trainRegion;
    private String counter;
    private String salesCode;
}
