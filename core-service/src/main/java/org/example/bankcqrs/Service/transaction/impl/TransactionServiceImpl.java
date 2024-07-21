package org.example.bankcqrs.Service.transaction.impl;

import com.example.common.domain.Model.Transaction;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.transaction.TransactionCommandService;
import com.example.common.Service.transaction.TransactionQueryService;
import org.example.bankcqrs.Service.transaction.TransactionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionCommandService commandService;

    private final TransactionQueryService queryService;
    @Override
    public void create(Transaction object) {
        commandService.create(object);
    }

    @Override
    public Transaction getById(UUID id) {
        return queryService.getById(id);
    }
}
