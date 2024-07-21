package org.example.bankcqrs.web.Controller;

import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.client.ClientService;
import com.example.common.domain.Model.Client;
import org.example.bankcqrs.web.dto.AccountDto;
import org.example.bankcqrs.web.dto.CardDto;
import org.example.bankcqrs.web.dto.ClientDto;
import org.example.bankcqrs.web.dto.mapper.AccountMapper;
import org.example.bankcqrs.web.dto.mapper.CardMapper;
import org.example.bankcqrs.web.dto.mapper.ClientMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final CardMapper  cardMapper;
    private final AccountMapper accountMapper;

    @GetMapping("/{id}")
    @PreAuthorize("@ssi.canAccessClient(#id)")
    public ClientDto getById(@PathVariable(name = "id") final UUID id) {
        Client client = clientService.getById(id);
        return clientMapper.toDto(client);
    }

    @GetMapping("/{id}/cards")
    @PreAuthorize("@ssi.canAccessClient(#id)")
    public List<CardDto> getCardsById(@PathVariable(name = "id") final UUID id) {
        Client client = clientService.getById(id);
        return cardMapper.toDto(client.getCards());
    }

    @GetMapping("/{id}/account")
    @PreAuthorize("@ssi.canAccessClient(#id)")
    public AccountDto getAccountById(@PathVariable(name = "id") final UUID id) {
        Client client = clientService.getById(id);
        return accountMapper.toDto(client.getAccount());
    }
}
