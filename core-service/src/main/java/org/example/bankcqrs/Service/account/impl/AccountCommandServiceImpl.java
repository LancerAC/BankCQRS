package org.example.bankcqrs.Service.account.impl;

import com.example.common.Events.AccountCreateEvent;
import com.example.common.domain.Model.Account;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.account.AccountCommandService;
import org.example.bankcqrs.Service.event.EventService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {

    private final EventService eventService;

    @Override
    public void create(Account object) {
        AccountCreateEvent event = new AccountCreateEvent(object);
        eventService.create(event);
    }
}
