package com.example.eventhandler.service.card;

import com.example.common.domain.Model.Card;

import java.math.BigDecimal;
import java.util.UUID;

public interface CardService {

    Card create(Card card);

    Card getById(UUID id);

    void add(Card card, BigDecimal amount);

    void addTransaction(UUID cardId, UUID transactionId);
}
