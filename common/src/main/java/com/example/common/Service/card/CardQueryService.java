package com.example.common.Service.card;

import com.example.common.domain.Model.Card;
import com.example.common.Service.QueryService;

public interface CardQueryService extends QueryService<Card> {

    boolean existsByNumberAndDate(String number, String date);

    Card getByNumberAndDateAndCvv(String number, String date, String cvv);

    Card getByNumberAndDate(String number, String date);
}
