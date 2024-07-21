package org.example.bankcqrs.web.Security;

import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.client.ClientService;
import com.example.common.domain.Model.Client;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientService.getByUsername(username);
        return new SecurityUser(client);
    }
}
