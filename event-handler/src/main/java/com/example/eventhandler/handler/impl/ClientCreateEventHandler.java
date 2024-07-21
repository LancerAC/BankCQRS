package com.example.eventhandler.handler.impl;

import com.example.common.Events.CardCreateEvent;
import com.example.common.Events.ClientCreateEvent;
import com.example.common.domain.Model.Card;
import com.example.common.domain.Model.Client;
import com.example.eventhandler.handler.EventHandler;
import com.example.eventhandler.service.card.CardService;
import com.example.eventhandler.service.client.ClientService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("CLIENT_CREATE")
public class ClientCreateEventHandler implements EventHandler {

    private final ClientService service;
    private final Gson gson;

    @Override
    @Transactional
    public void handle(JsonObject object, Acknowledgment acknowledgment) {
        ClientCreateEvent event = gson.fromJson(
                object,
                ClientCreateEvent.class
        );
        Client client = gson.fromJson(
                (String) event.getPayload(),
                Client.class
        );
        service.create(client);
        acknowledgment.acknowledge();
    }
}
