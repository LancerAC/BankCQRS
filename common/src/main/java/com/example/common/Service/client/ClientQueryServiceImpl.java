package com.example.common.Service.client;

import lombok.RequiredArgsConstructor;
import com.example.common.Repository.ClientRepository;
import com.example.common.domain.Exceptions.ResourceNotFoundException;
import com.example.common.domain.Model.Client;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {
    private final ClientRepository repository;

    @Override
    public Client getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Client getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public Client getByAccount(UUID id) {
        return repository.findByAccountId(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
