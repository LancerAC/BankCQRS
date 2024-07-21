package com.example.eventhandler.service.client.impl;

import com.example.common.Repository.ClientRepository;
import com.example.common.domain.Model.Account;
import com.example.common.domain.Model.Client;
import com.example.eventhandler.service.account.AccountService;
import com.example.eventhandler.service.client.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final AccountService accountService;

    @Override
    @Transactional
    public Client create(
            final Client client
    ) {
        Account account = new Account();
        account = accountService.create(account);
        client.setAccount(account);
        return repository.save(client);
    }

    @Override
    @Transactional
    public void addCard(
            final UUID clientId,
            final UUID cardId
    ) {
        repository.addCard(
                clientId.toString(),
                cardId.toString()
        );
    }

}
