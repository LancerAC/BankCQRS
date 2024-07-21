package com.example.eventhandler.handler.impl;

import com.example.common.Events.ClientCreateEvent;
import com.example.common.Events.TransactionCreateEvent;
import com.example.common.domain.Model.Client;
import com.example.common.domain.Model.Transaction;
import com.example.eventhandler.handler.EventHandler;
import com.example.eventhandler.service.client.ClientService;
import com.example.eventhandler.service.transaction.TransactionService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("TRANSACTION_CREATE")
public class TransactionCreateEventHandler implements EventHandler {

    private final TransactionService service;
    private final Gson gson;

    @Override
    @Transactional
    public void handle(JsonObject object, Acknowledgment acknowledgment) {
        TransactionCreateEvent event = gson.fromJson(
                object,
                TransactionCreateEvent.class
        );
        Transaction transaction = gson.fromJson(
                (String) event.getPayload(),
                Transaction.class
        );
        service.create(transaction);
        acknowledgment.acknowledge();
    }
}

