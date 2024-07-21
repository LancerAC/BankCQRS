package org.example.bankcqrs.Service.transaction;

import com.example.common.domain.Model.Transaction;
import org.example.bankcqrs.Service.CommandService;

public interface TransactionCommandService extends CommandService<Transaction> {
}
