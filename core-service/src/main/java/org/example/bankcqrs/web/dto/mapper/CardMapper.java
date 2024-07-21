package org.example.bankcqrs.web.dto.mapper;

import com.example.common.domain.Model.Card;
import org.example.bankcqrs.web.dto.CardDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper extends Mappable<Card, CardDto> {
}
