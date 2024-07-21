package org.example.bankcqrs.Service.auth.impl;

import com.example.common.domain.Exceptions.ResourceAlreadyExistException;
import com.example.common.domain.Model.Client;
import io.github.ilyalisov.jwt.config.TokenParameters;
import io.github.ilyalisov.jwt.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.auth.AuthService;
import org.example.bankcqrs.Service.client.ClientService;
import org.example.bankcqrs.web.Security.jwt.JwtProperties;
import org.example.bankcqrs.web.Security.jwt.TokenType;
import org.example.bankcqrs.web.dto.LoginRequestDto;
import org.example.bankcqrs.web.dto.LoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ClientService clientService;
    private final TokenService tokenService;
    private final JwtProperties jwtProperties;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        LoginResponseDto response = new LoginResponseDto();
        response.setAccess(
                tokenService.create(
                        TokenParameters.builder(
                                loginRequestDto.getUsername(),
                                        TokenType.ACCESS.name(),
                                        jwtProperties.getAccess())
                                .build()
                )
        );
        response.setRefresh(
                tokenService.create(
                        TokenParameters.builder(
                                loginRequestDto.getUsername(),
                                TokenType.REFRESH.name(),
                                jwtProperties.getRefresh()
                        ).build()
                )
        );
        return response;
    }

    @Override
    public void register(Client client) {
        if(clientService.existsByUsername(client.getUsername())){
            throw new ResourceAlreadyExistException();
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientService.create(client);
    }
}
