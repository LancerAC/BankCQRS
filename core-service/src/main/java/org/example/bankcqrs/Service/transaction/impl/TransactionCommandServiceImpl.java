package org.example.bankcqrs.Service.transaction.impl;

import com.example.common.Events.TransactionCreateEvent;
import com.example.common.domain.Model.Transaction;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.event.EventService;
import org.example.bankcqrs.Service.transaction.TransactionCommandService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCommandServiceImpl implements TransactionCommandService {

    private final EventService eventService;

    @Override
    public void create(Transaction object) {
        TransactionCreateEvent event = new TransactionCreateEvent(object);
        eventService.create(event);
    }
}
