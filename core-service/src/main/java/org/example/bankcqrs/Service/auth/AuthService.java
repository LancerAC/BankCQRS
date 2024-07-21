package org.example.bankcqrs.Service.auth;

import com.example.common.domain.Model.Client;
import org.example.bankcqrs.web.dto.LoginRequestDto;
import org.example.bankcqrs.web.dto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    void register(Client client);
}
