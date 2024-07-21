package org.example.bankcqrs.Service.account;

import com.example.common.domain.Model.Account;
import org.example.bankcqrs.Service.CommandService;
import com.example.common.Service.QueryService;

public interface AccountService extends QueryService<Account>, CommandService<Account> {
}
