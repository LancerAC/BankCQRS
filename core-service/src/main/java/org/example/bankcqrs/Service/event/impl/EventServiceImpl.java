package org.example.bankcqrs.Service.event.impl;

import com.example.common.Events.AbstractEvent;
import com.example.common.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.event.EventService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Override
    public void create(AbstractEvent event) {
        repository.save(event);
    }
}
