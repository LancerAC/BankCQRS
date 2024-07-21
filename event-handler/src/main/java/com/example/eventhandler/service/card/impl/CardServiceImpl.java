package com.example.eventhandler.service.card.impl;

import com.example.common.Repository.CardRepository;
import com.example.common.domain.Exceptions.ResourceNotFoundException;
import com.example.common.domain.Model.Card;
import com.example.common.domain.Model.Client;
import com.example.eventhandler.service.card.CardService;
import com.example.eventhandler.service.client.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.common.Service.client.ClientQueryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repository;
    private final ClientQueryService clientQueryService;
    private final ClientService clientService;

    @Override
    public Card getById(
            final UUID id
    ) {
        return repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Card create(
            final Card card
    ) {
        card.setCvv(generateCvv());
        card.setDate(generateDate());
        card.setNumber(generateNumber());
        repository.save(card);
        Client client = clientQueryService.getByAccount(
                card.getAccount().getId()
        );
        clientService.addCard(client.getId(), card.getId());
        return card;
    }

    private String generateCvv() {
        return String.valueOf(100 + (int) (Math.random() * 899));
    }

    private String generateDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusYears(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        return expirationDate.format(formatter);
    }

    private String generateNumber() {
        return String.format(
                "%04d%04d%04d%04d",
                1000 + (int) (Math.random() * 8999),
                1000 + (int) (Math.random() * 8999),
                1000 + (int) (Math.random() * 8999),
                1000 + (int) (Math.random() * 8999)
        );
    }

    @Override
    @Transactional
    public void add(
            final Card card,
            final BigDecimal amount
    ) {
        card.getAccount().setBalance(
                card.getAccount().getBalance().add(amount)
        );
        repository.save(card);
    }

    @Override
    @Transactional
    public void addTransaction(
            final UUID cardId,
            final UUID transactionId
    ) {
        repository.addTransaction(
                cardId.toString(),
                transactionId.toString()
        );
    }

}
