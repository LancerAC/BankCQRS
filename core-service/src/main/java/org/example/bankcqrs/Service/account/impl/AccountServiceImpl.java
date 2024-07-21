package org.example.bankcqrs.Service.account.impl;

import com.example.common.domain.Model.Account;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.account.AccountCommandService;
import com.example.common.Service.account.AccountQueryService;
import org.example.bankcqrs.Service.account.AccountService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountQueryService queryService;

    private final AccountCommandService commandService;

    @Override
    public void create(Account object) {
        commandService.create(object);
    }

    @Override
    public Account getById(UUID id) {
        return queryService.getById(id);
    }
}
