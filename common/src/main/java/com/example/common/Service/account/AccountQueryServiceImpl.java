package com.example.common.Service.account;

import com.example.common.Repository.AccountRepository;
import com.example.common.domain.Exceptions.ResourceNotFoundException;
import com.example.common.domain.Model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository repository;

    @Override
    public Account getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
