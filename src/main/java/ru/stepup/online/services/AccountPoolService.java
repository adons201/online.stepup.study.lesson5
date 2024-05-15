package ru.stepup.online.services;

import org.springframework.stereotype.Service;
import ru.stepup.online.dto.request.TppProductRegisterDtoRequest;
import ru.stepup.online.entity.AccountPool;
import ru.stepup.online.repo.AccountPoolRepository;

import java.util.Optional;

@Service
public class AccountPoolService {
    private AccountPoolRepository accountPoolRepository;

    public AccountPoolService(AccountPoolRepository accountPoolRepository) {
        this.accountPoolRepository = accountPoolRepository;
    }

    public Optional<AccountPool> findFirstAccountPullId(TppProductRegisterDtoRequest tppProductRegisterDto) {
        Integer first = accountPoolRepository.findFirst(tppProductRegisterDto.getBranchCode(),
                tppProductRegisterDto.getCurrencyCode(),
                tppProductRegisterDto.getMdmCode(),
                tppProductRegisterDto.getPriorityCode(),
                tppProductRegisterDto.getRegistryTypeCode());
        return accountPoolRepository.findById(first);
    }



}
