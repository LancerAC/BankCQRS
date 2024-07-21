package org.example.bankcqrs.web.Security.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.card.CardService;
import org.example.bankcqrs.Service.client.ClientService;
import org.example.bankcqrs.Service.transaction.TransactionService;
import com.example.common.domain.Exceptions.ResourceNotFoundException;
import com.example.common.domain.Model.Card;
import com.example.common.domain.Model.Client;
import com.example.common.domain.Model.Transaction;
import org.example.bankcqrs.web.Security.SecurityUser;
import org.example.bankcqrs.web.Security.service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("ssi")
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final ClientService clientService;
    private final CardService cardService;
    private final TransactionService transactionService;

    @Override
    public SecurityUser getUserFromRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()) {
            return null;
        }
        if(authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }
        return (SecurityUser) authentication.getPrincipal();
    }

    @Override
    public boolean canAccessClient(UUID clientId) {

        SecurityUser user = getUserFromRequest();
        UUID id = user.getId();
        return clientId.equals(id);
    }

    @Override
    public boolean canAccessCard(UUID cardId) {
        SecurityUser user = getUserFromRequest();
        UUID id = user.getId();
        Client client = clientService.getById(id);
        return client.getCards().stream().anyMatch(c -> c.getId().equals(cardId));
    }

    @Override
    public boolean canAccessCard(Card card) {
        try {
            Card cardFound = cardService.getByNumberAndDateAndCvv(
                    card.getNumber(),
                    card.getDate(),
                    card.getCvv()
            );
            return canAccessCard(cardFound.getId());
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean canAccessTransaction(UUID transactionId) {
        SecurityUser security = getUserFromRequest();
        UUID id = security.getId();
        Client client = clientService.getById(id);
        Transaction transaction = transactionService.getById(transactionId);
        return client.getCards().contains(transaction.getFrom())
                || client.getCards().contains(transaction.getTo());
    }
}
