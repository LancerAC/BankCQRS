package org.example.bankcqrs.web.Security;

import lombok.Data;
import com.example.common.domain.Model.Client;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
public class SecurityUser implements UserDetails {

    private final UUID id;

    private final String username;
    private final String password;
    private final Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(
            final Client user
    ) {
        this(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
        this.authorities.add(
                mapToGrantedAuthorities("ROLE_USER")
        );
    }

    public SecurityUser(
            UUID id,
            String username,
            String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = new ArrayList<>();
    }

    private static SimpleGrantedAuthority mapToGrantedAuthorities(
            final String role
    ) {
        return new SimpleGrantedAuthority(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
