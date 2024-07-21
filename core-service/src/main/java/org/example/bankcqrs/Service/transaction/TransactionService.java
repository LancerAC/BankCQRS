package org.example.bankcqrs.Service.transaction;

import com.example.common.domain.Model.Transaction;
import org.example.bankcqrs.Service.CommandService;
import com.example.common.Service.QueryService;

public interface TransactionService extends CommandService<Transaction>, QueryService<Transaction> {
}
