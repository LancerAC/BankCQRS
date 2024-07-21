package com.example.common.Service.transaction;

import com.example.common.Repository.TransactionRepository;
import com.example.common.domain.Exceptions.ResourceNotFoundException;
import com.example.common.domain.Model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {
    private final TransactionRepository repository;

    @Override
    public Transaction getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
