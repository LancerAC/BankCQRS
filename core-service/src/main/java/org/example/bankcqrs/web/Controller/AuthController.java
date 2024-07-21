package org.example.bankcqrs.web.Controller;

import com.example.common.domain.Model.Client;
import lombok.RequiredArgsConstructor;
import org.example.bankcqrs.Service.auth.AuthService;
import org.example.bankcqrs.web.dto.ClientDto;
import org.example.bankcqrs.web.dto.LoginRequestDto;
import org.example.bankcqrs.web.dto.LoginResponseDto;
import org.example.bankcqrs.web.dto.OnCreate;
import org.example.bankcqrs.web.dto.mapper.ClientMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private final ClientMapper clientMapper;

    @PostMapping("/register")
    public void register(@RequestBody @Validated(OnCreate.class) final ClientDto clientDto) {
        Client client = clientMapper.fromDto(clientDto);
        authService.register(client);
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody @Validated final LoginRequestDto dto
    ) {
        return authService.login(dto);
    }
}
