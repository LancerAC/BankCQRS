package org.example.bankcqrs.web.Security.service;


import com.example.common.domain.Model.Card;
import org.example.bankcqrs.web.Security.SecurityUser;

import java.util.UUID;

public interface SecurityService {

    SecurityUser getUserFromRequest();

    boolean canAccessClient(UUID id);

    boolean canAccessCard(UUID id);

    boolean canAccessCard(Card card);

    boolean canAccessTransaction(UUID id);
}
