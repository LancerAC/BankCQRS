package org.example.bankcqrs.Service.card.impl;

import com.example.common.domain.Model.Card;
import com.example.common.domain.Model.Client;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.card.CardCommandService;
import com.example.common.Service.card.CardQueryService;
import org.example.bankcqrs.Service.card.CardService;
import org.example.bankcqrs.Service.client.ClientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardQueryService queryService;
    private final ClientService clientService;
    private final CardCommandService commandService;

    @Override
    public void create(Card object) {
        commandService.create(object);
    }

    @Override
    public Card getById(UUID id) {
        return queryService.getById(id);
    }

    @Override
    public void createByClientId(UUID id) {
        Client client = clientService.getById(id);
        Card  card = new Card(client.getAccount());
        commandService.create(card);
    }

    @Override
    public boolean existsByNumberAndDate(String number, String date) {
        return queryService.existsByNumberAndDate(number, date);
    }

    @Override
    public Card getByNumberAndDate(String number, String date) {
        return queryService.getByNumberAndDate(number, date);
    }

    @Override
    public Card getByNumberAndDateAndCvv(String number, String date, String cvv) {
        return queryService.getByNumberAndDateAndCvv(
                number,
                date,
                cvv
        );
    }
}
