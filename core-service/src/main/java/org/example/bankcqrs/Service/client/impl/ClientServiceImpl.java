package org.example.bankcqrs.Service.client.impl;

import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.client.ClientCommandService;
import com.example.common.Service.client.ClientQueryService;
import org.example.bankcqrs.Service.client.ClientService;
import com.example.common.domain.Model.Client;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientQueryService queryService;

    private final ClientCommandService commandService;

    @Override
    public void create(Client object) {
        commandService.create(object);
    }

    @Override
    public Client getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return queryService.existsByUsername(username);
    }

    @Override
    public Client getByUsername(String username) {
        return queryService.getByUsername(username);
    }
}
