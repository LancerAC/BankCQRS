package com.example.eventhandler.handler.impl;

import com.example.common.Events.CardCreateEvent;
import com.example.common.domain.Model.Card;
import com.example.eventhandler.handler.EventHandler;
import com.example.eventhandler.service.card.CardService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("CARD_CREATE")
public class CardCreateEventHandler implements EventHandler {

    private final CardService service;
    private final Gson gson;

    @Override
    @Transactional
    public void handle(JsonObject object, Acknowledgment acknowledgment) {
        CardCreateEvent event = gson.fromJson(
                object,
                CardCreateEvent.class
        );
        Card card = gson.fromJson(
                (String) event.getPayload(),
                Card.class
        );
        service.create(card);
        acknowledgment.acknowledge();
    }
}
