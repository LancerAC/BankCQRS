package com.example.eventhandler.handler.impl;

import com.example.common.Events.AccountCreateEvent;
import com.example.common.domain.Model.Account;
import com.example.eventhandler.handler.EventHandler;
import com.example.eventhandler.service.account.AccountService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("ACCOUNT_CREATE")
public class AccountCreateEventHandler implements EventHandler {

    private final AccountService accountService;
    private final Gson gson;

    @Override
    @Transactional
    public void handle(JsonObject object, Acknowledgment acknowledgment) {
        AccountCreateEvent event = gson.fromJson(
                object,
                AccountCreateEvent.class
        );
        Account account = gson.fromJson(
                (String) event.getPayload(),
                Account.class
        );
        accountService.create(account);
        acknowledgment.acknowledge();
    }
}
