package com.example.common.Service.client;

import com.example.common.domain.Model.Client;
import com.example.common.Service.QueryService;

import java.util.UUID;

public interface ClientQueryService extends QueryService<Client> {
    boolean existsByUsername(String username);

    Client getByUsername(String username);

    Client getByAccount(UUID id);
}
