package ru.stepup.online.mapper;

import ru.stepup.online.entity.Agreement;
import ru.stepup.online.model.AgreementModel;

public interface AgreementMapper {
    AgreementModel agreementToAgreementModel(Agreement agreement);
    Agreement agreementModelToAgreement(AgreementModel agreementModel);
}
