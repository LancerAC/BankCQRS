package org.example.bankcqrs.Service.client;

import com.example.common.domain.Model.Client;
import org.example.bankcqrs.Service.CommandService;
import com.example.common.Service.QueryService;

import java.util.UUID;

public interface ClientService extends CommandService<Client>, QueryService<Client> {
    boolean existsByUsername(String username);

    Client getByUsername(String username);
}
