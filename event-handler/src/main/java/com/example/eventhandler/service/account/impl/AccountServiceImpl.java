package com.example.eventhandler.service.account.impl;

import com.example.common.Repository.AccountRepository;
import com.example.common.domain.Model.Account;
import com.example.eventhandler.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public Account create(Account account) {
        account.setBalance(BigDecimal.ZERO);
        account.setNumber(UUID.randomUUID().toString());
        return repository.save(account);
    }
}
