package org.example.bankcqrs.web.dto.mapper;

import com.example.common.domain.Model.Client;
import org.example.bankcqrs.web.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends Mappable<Client, ClientDto> {
}
