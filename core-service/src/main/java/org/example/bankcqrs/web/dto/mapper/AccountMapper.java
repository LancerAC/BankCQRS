package org.example.bankcqrs.web.dto.mapper;

import com.example.common.domain.Model.Account;
import org.example.bankcqrs.web.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends Mappable<Account, AccountDto> {}
