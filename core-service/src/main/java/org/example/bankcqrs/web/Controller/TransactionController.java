package org.example.bankcqrs.web.Controller;

import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.card.CardService;
import org.example.bankcqrs.Service.transaction.TransactionService;
import com.example.common.domain.Model.Transaction;
import org.example.bankcqrs.web.dto.OnCreate;
import org.example.bankcqrs.web.dto.TransactionDto;
import org.example.bankcqrs.web.dto.mapper.TransactionMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CardService cardService;
    private final TransactionMapper transactionMapper;

    @PostMapping
    @PreAuthorize("@ssi.canAccessCard(#transactionDto.from)")
    public void create(
            @RequestBody @Validated(OnCreate.class) TransactionDto transactionDto
    ) {
        if(!cardService.existsByNumberAndDate(
                transactionDto.getTo().getNumber(),
                transactionDto.getTo().getDate()
        )) {
          throw new IllegalArgumentException("Card not found");
        }
        Transaction transaction = transactionMapper.fromDto(transactionDto);
        transactionService.create(transaction);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ssi.canAccessTransaction(#id)")
    public TransactionDto getById(@PathVariable final UUID id) {
        Transaction transaction = transactionService.getById(id);
        return transactionMapper.toDto(transaction);
    }
}
