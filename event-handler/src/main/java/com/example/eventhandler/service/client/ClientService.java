package com.example.eventhandler.service.client;

import com.example.common.domain.Model.Client;

import java.util.UUID;

public interface ClientService {

    Client create(Client client);

    void addCard(UUID clientId, UUID cardId);
}
