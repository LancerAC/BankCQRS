package org.example.bankcqrs.web.Controller;

import com.example.common.domain.Model.Card;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.card.CardService;
import org.example.bankcqrs.web.Security.SecurityUser;
import org.example.bankcqrs.web.Security.service.SecurityService;
import org.example.bankcqrs.web.dto.CardDto;
import org.example.bankcqrs.web.dto.TransactionDto;
import org.example.bankcqrs.web.dto.mapper.CardMapper;
import org.example.bankcqrs.web.dto.mapper.TransactionMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final SecurityService securityService;
    private final CardService cardService;
    private final CardMapper cardMapper;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public void create(){
        SecurityUser user = securityService.getUserFromRequest();
        cardService.createByClientId(user.getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ssi.canAccessCard(#id)")
    public CardDto getById(
            @PathVariable final UUID id
    ) {
        Card card = cardService.getById(id);
        return cardMapper.toDto(card);
    }

    @GetMapping("/{id}/transactions")
    @PreAuthorize("@ssi.canAccessCard(#id)")
    public List<TransactionDto> getTransactionsById(
            @PathVariable final UUID id
    ) {
        Card card = cardService.getById(id);
        return transactionMapper.toDto(card.getTransactions());
    }

}
