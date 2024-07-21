package org.example.bankcqrs.Service.card;

import com.example.common.domain.Model.Card;
import org.example.bankcqrs.Service.CommandService;
import com.example.common.Service.QueryService;

import java.util.UUID;

public interface CardService extends CommandService<Card>, QueryService<Card> {

    void createByClientId(UUID id);

    boolean existsByNumberAndDate(String number, String date);

    Card getByNumberAndDate(String number, String date);

    Card getByNumberAndDateAndCvv(String number, String date, String cvv);
}


