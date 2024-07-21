package org.example.bankcqrs.Service.client.impl;

import lombok.RequiredArgsConstructor;
import com.example.common.Events.ClientCreateEvent;
import org.example.bankcqrs.Service.client.ClientCommandService;
import org.example.bankcqrs.Service.event.EventService;
import com.example.common.domain.Model.Client;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientCommandServiceImpl implements ClientCommandService {

    private final EventService eventService;

    @Override
    public void create(Client object) {
        ClientCreateEvent event = new ClientCreateEvent(object);
        eventService.create(event);
    }
}
