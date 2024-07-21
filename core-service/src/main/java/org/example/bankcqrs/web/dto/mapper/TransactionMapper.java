package org.example.bankcqrs.web.dto.mapper;

import com.example.common.domain.Model.Transaction;
import org.example.bankcqrs.web.dto.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends Mappable<Transaction, TransactionDto> {
}
