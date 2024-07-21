package org.example.bankcqrs.Service.card.impl;

import com.example.common.Events.CardCreateEvent;
import com.example.common.domain.Model.Card;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.card.CardCommandService;
import org.example.bankcqrs.Service.event.EventService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardCommandServiceImpl implements CardCommandService {

    private final EventService eventService;

    @Override
    public void create(Card object) {
        CardCreateEvent event = new CardCreateEvent(object);
        eventService.create(event);
    }
}
